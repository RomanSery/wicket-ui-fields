package org.coderdreams.wicketfields.fields.bool;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;
import org.coderdreams.wicketfields.model.CheckBoxGroupModel;

public class CheckBoxGroupField extends BaseUiField<List<CheckBoxGroupModel>> implements IAjaxUiField {
	private static final long serialVersionUID = 1L;

    public CheckBoxGroupField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {

        addOrReplace(new ListView<CheckBoxGroupModel>("chkListView", model) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(ListItem<CheckBoxGroupModel> item) {
				CheckBox chkBox = new CheckBox("chkBox", item.getModelObject().getModel());
				chkBox.setLabel(Model.of(item.getModelObject().getLabel()));		
				item.addOrReplace(chkBox.setOutputMarkupId(true));
				
				item.addOrReplace(new Label("lbl", item.getModelObject().getLabel()).setEscapeModelStrings(false));

				if(isAjax()) {
                    chkBox.add(new AjaxFormComponentUpdatingBehavior("click") {
                        private static final long serialVersionUID = 1L;
                        @Override
                        protected void onUpdate(AjaxRequestTarget target) {
                            onFieldChanged(target);
                            clientScripts(target);
                        }
                    });
                }
			}
		});
	}

    protected boolean isAjax() { return false; }

    @Override
    protected String getDisabledLbl() {
        if(model == null || model.getObject() == null || model.getObject().isEmpty()) {
            return "";
        }
        List<String> arr = new ArrayList<String>();
        for(CheckBoxGroupModel c : model.getObject()) {
            if(BooleanUtils.isTrue(c.getModel().getObject())) arr.add(c.getLabel());
        }
        return StringUtils.join(arr, "<br />");
	}
    @Override
    protected String getInnerHtml() {
        return "<span wicket:id=\"chkListView\">" +
                "<label class=\"col-xs-12\"> <input type=\"checkbox\" class=\"ace ace-checkbox-2\" wicket:id=\"chkBox\" /> <span class=\"lbl\" wicket:id=\"lbl\">label</span> </label>" +
                "</span>";
    }
    @Override protected Class getDefiniteType() { return CheckBoxGroupModel.class; }

	@Override
	public FormComponent<List<CheckBoxGroupModel>> getField() {
		return null;
	}

	
}
