package org.coderdreams.wicketfields.fields.bool;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.CheckBox;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxSwitchField extends BaseUiField<Boolean> implements IAjaxUiField {
	
	private static final long serialVersionUID = 1L;		
	private CheckBox fieldInput;

    public AjaxSwitchField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {

		fieldInput = new CheckBox("fieldInput", model);
        addOrReplace(fieldInput.setOutputMarkupId(true));
		
		fieldInput.add(new AjaxFormComponentUpdatingBehavior("click") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
                onFieldChanged(target);
                clientScripts(target);
				
			}
		});
	}

    @Override protected String getDisabledLbl() { return model == null || model.getObject() == null ? "" : (model.getObject() == true ? "Yes" : "No"); }
    @Override public Class<Boolean> getDefiniteType() { return Boolean.class; }
    @Override
    protected String getInnerHtml() {
        return "<input type=\"checkbox\" class=\"custom-control-input wf-switch-field\" wicket:id=\"fieldInput\" /> <div class=\"lbl middle\"></div>";
    }
	@Override public CheckBox getField() {
		return fieldInput;
	}
}
