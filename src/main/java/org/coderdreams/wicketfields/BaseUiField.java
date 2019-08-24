package org.coderdreams.wicketfields;

import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.feedback.FeedbackCollector;
import org.apache.wicket.feedback.IFeedback;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.IMarkupCacheKeyProvider;
import org.apache.wicket.markup.IMarkupResourceStreamProvider;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.AbstractChoice;
import org.apache.wicket.markup.html.form.AbstractSingleSelectChoice;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.resource.IResourceStream;
import org.apache.wicket.util.resource.StringResourceStream;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.StringValidator;
import org.coderdreams.wicketfields.event.InitPanelFieldsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseUiField<T> extends Panel implements IMarkupResourceStreamProvider, IMarkupCacheKeyProvider, IFeedback {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseUiField.class);

	static final int DEF_L = 4;
	static final int DEF_R = 8;

    private final static String markup =
                    "<wicket:panel>" +
                    "<div class=\"form-group row\">" +
                    "   <label class=\"col-form-label col-sm-{LCLASS}\"> <span wicket:id=\"fieldLabel\"></span> </label>" +
                    "   <div class=\"{RCLASS} {CUSTOMRCLASS}\">{INNERHTML} {DISABLEDHTML} <label class=\"{CNTRL_LBL}\" wicket:id=\"fLabel\"></label></div>" +
                    "</div>" +
                    "</wicket:panel>";
    private final static String disabledHtml = "<div wicket:id=\"fieldInputDisabled\" class=\"col-xs-10\"></div>";
    private final static String REQUIRED = "<span class=\"text-danger\">*</span>";

	protected IModel<T> model;
    protected String fieldLabel;
    private final String propertiesId;
	private int l = DEF_L, r = DEF_R;
	
	private IModel<Boolean> enabledModel;
	private String helpTxt;
    protected String nullChoiceTxt;
    protected IChoiceRenderer cr;
    private Class<T> inputType;

    private String markupId;
    private int maxLength;
    private final boolean forceDisable;

    public BaseUiField(FieldArgs args) {
        super(args.getId(), args.getModel());

        this.forceDisable = args.isForceDisable();
        this.model = args.getModel();
        this.propertiesId = args.getPropertiesId();
        this.fieldLabel = args.getPropertiesId() == null ? args.getFieldLabel() : getString(propertiesId + ".lbl", null, args.getFieldLabel());
        this.l = args.getL();
        this.r = args.getR();
        this.markupId = args.getMarkupId();
        this.maxLength = args.getMaxLength();

        if(propertiesId != null) {
            this.helpTxt = getHelpText();
            setVisibilityAllowed(!BooleanUtils.toBoolean(getString(propertiesId + ".hidden", null, "0"), "1", "0"));
            nullChoiceTxt = getString(propertiesId + ".nullChoice", null, "");
        } else {
            nullChoiceTxt = "";
        }

        this.cr = args.getCr();
        this.inputType = args.getInputType();

        assignFromArgs(args);
    }


    @Override
    public void onEvent(IEvent<?> event) {
        super.onEvent(event);
        if (event.getPayload() instanceof InitPanelFieldsEvent) {
            baseInitField();
        }
    }


    @Override
    protected void onBeforeRender() {
        addOrReplace(new Literal("fieldLabel", getFieldLabel()));
        super.onBeforeRender();
    }

	private void baseInitField() {
        if(!isVisibilityAllowed()) {
            return;
        }

        //TODO
//        IPanel bp = getParentPanel();
//		if(bp != null) {
//            enabledModel = bp.getEnabledModel();
//        }

        boolean baseIsEnabled = baseIsEnabled();
        if(hasDisabledLbl() && !baseIsEnabled) {
            addOrReplace(new Label("fieldInputDisabled", this::getDisabledValue).setEscapeModelStrings(false));
            initDisabledField();
        } else if(!hasDisabledLbl() && !baseIsEnabled) {
            initDisabledField();
        } else {
            initField();

            if(!StringUtils.isBlank(this.markupId) && hasInputField()) {
                getField().setMarkupId(this.markupId);
            }
            if(this.maxLength > 0 && hasInputField()) {
                getField().add(StringValidator.maximumLength(this.maxLength));
            }

            addOrReplace(new WebComponent("fLabel") {
                private static final long serialVersionUID = 1L;
                @Override
                protected void onComponentTag(final ComponentTag tag) {
                    super.onComponentTag(tag);
                    if(hasInputField()) {
                        tag.put("for", getField().getMarkupId());
                    }
                }
            });

            initialize();
        }
	}

	@Override 
	public final boolean isEnabled() {
        if(forceDisable) {
            return false;
        }

        return enabledModel == null ? super.isEnabled() : !enabledModel.getObject();
	}

    public boolean baseIsEnabled() { return isEnabled(); }

	@Override
	public void onConfigure() {
        super.onConfigure();
        this.setOutputMarkupId(true);
        this.setOutputMarkupPlaceholderTag(true);
	}

    @Override
    public IResourceStream getMarkupResourceStream(MarkupContainer container, Class<?> containerClass) {

        boolean baseIsEnabled = baseIsEnabled();
        boolean disabled = hasDisabledLbl() && !baseIsEnabled;
        String innerHtml = disabled ? "" : getInnerHtml();
        if(!hasDisabledLbl() && !baseIsEnabled) {
            String disabledHtml = getDisabledInnerHtml();
            innerHtml = disabledHtml != null ? disabledHtml : getInnerHtml();
        }
        String str = StringUtils.replaceEach(getBaseMarkup(),
                new String[]{"{LCLASS}", "{RCLASS}", "{CUSTOMRCLASS}", "{CNTRL_LBL}", "{INNERHTML}", "{DISABLEDHTML}"},
                new String[]{String.valueOf(l), getColRightClass(), getCustomRightClass(), getControlLblClass(), innerHtml,
                        disabled ? disabledHtml : ""
                });

        return new StringResourceStream(str);
    }

    @Override
    public String getCacheKey(MarkupContainer container, Class<?> containerClass) {
        final String classname = containerClass.isAnonymousClass() ? containerClass.getSuperclass().getSimpleName() : containerClass.getSimpleName();
        return classname + '_' + l + '_' + r + '_' +
                (hasDisabledLbl() ? 1 : 0) + '_' +
                (baseIsEnabled() ? 1 : 0);
    }

    @Override
    protected void onComponentTag(final ComponentTag tag) {
        super.onComponentTag(tag);
        FeedbackCollector fc = new FeedbackCollector(this);
        boolean hasMessages = fc.collect(new ContainerFeedbackMessageFilter(this)).size() > 0;
        if(hasMessages) {
            tag.put("class", "hasErrors");
        }
    }

    protected void clientScripts(AjaxRequestTarget target) {
        if(target == null) {
            return;
        }
        target.appendJavaScript("wicketUiFieldScripts.initClientSideScripts();");
    }

    protected Class<T> getFormFieldType(Class defaultClass) {
        if(getDefiniteType() != null) {
            return getDefiniteType();
        }
        if(inputType != null) {
            return inputType;
        }
        return defaultClass;
    }

    private String getFieldLabel() {
        String lbl = StringUtils.replace(fieldLabel, "{REQUIRED}", REQUIRED);
        lbl = getCustomLabel(lbl);
        if(!StringUtils.isBlank(helpTxt)) {
            lbl += " <a href=\"javascript:;\" class=\"wicket-fields-popover\" tabindex=\"0\" role=\"button\" data-rel=\""+ getPopoverClass()+"\" data-trigger=\"focus hover\" title=\"Tip\" data-content=\""+ StringEscapeUtils.escapeHtml4(helpTxt)+"\"><i class=\"ace-icon fa fa-question-circle\"></i></a>";
        }
        return lbl;
    }

    protected void nullValid(boolean nullValid) {
        if(getField() instanceof AbstractSingleSelectChoice) {
            ((AbstractSingleSelectChoice<T>) getField()).setNullValid(nullValid);
        }
    }
    protected void addBehavior(final Behavior behavior) {
        if(hasInputField()) {
            getField().add(behavior);
        }
    }
    protected void addValidator(final IValidator validator) {
        if(hasInputField()) {
            getField().add(validator);
        }
    }
    public void fieldModelChanged() {
        if(hasInputField()) {
            getField().modelChanged();
        }
    }
    public void setCr(IChoiceRenderer<T> cr) {
        this.cr = cr;
        if(getField() instanceof AbstractChoice) {
            ((AbstractChoice) getField()).setChoiceRenderer(cr);
        }
    }
    protected void setFieldChoices(List choices) {
        if(getField() instanceof AbstractChoice) {
            ((AbstractChoice) getField()).setChoices(choices);
        }
    }

    public boolean hasInputField() {
        return getField() != null;
    }

    private String getDisabledValue() {
        try {
            String lbl = customDisabledMsg();
            if(lbl != null) {
                return lbl;
            }

            lbl = getDisabledLbl();
            if(lbl != null) {
                return lbl;
            }

            if(model == null) {
                return "";
            }

            T obj = model.getObject();
            if(obj == null) {
                return "";
            }

            String str = cr != null ? (String)cr.getDisplayValue(obj) : (obj.toString());
            if(str != null) {
                str = StringUtils.replace(str, "\r\n", "<br />");
            }
            return str;
        } catch (Exception e) {
            LOGGER.error("failed getDisabledValue", e);
            return "";
        }
    }


    //override to assign additional fields specifc to the component, ex: WysiwygFileUploadPanel
    protected void assignFromArgs(FieldArgs args) {};
    protected void initialize() {}

    public String getPropertiesId() { return propertiesId; }
	protected abstract String getInnerHtml();
    protected String getDisabledInnerHtml() { return null; }
	protected boolean hasDisabledLbl() { return true; }
	protected String getBaseMarkup() { return markup; }
    protected String getColRightClass() { return "col-sm-" + r; }
    protected String getCustomRightClass() { return ""; }
    protected String getControlLblClass() { return "control-label"; }
    protected String getDisabledLbl() { return null; }
    protected String customDisabledMsg() { return null; }
    protected Class getDefiniteType() { return null; }
    public abstract FormComponent<T> getField();
    protected abstract void initField();
    protected void initDisabledField() {}

    public IChoiceRenderer getCr() { return cr; }

    protected String getHelpText() {
        return getString(propertiesId + ".help", null, "");
    }
    protected String getPopoverClass() { return "popover"; }

    public void setEnabledModel(IModel<Boolean> enabledModel) {
        this.enabledModel = enabledModel;
    }
    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }


    protected String getCustomLabel(String lbl) {
        return lbl;
    }
}
