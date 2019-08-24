package org.coderdreams.wicketfields.fields.bool;

import org.apache.wicket.markup.html.form.CheckBox;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class CheckBoxField extends BaseUiField<Boolean> {
	
	private static final long serialVersionUID = 1L;			
	private CheckBox fieldInput;

    public CheckBoxField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		fieldInput = new CheckBox("fieldInput", model);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

    @Override protected String getDisabledLbl() { return model == null || model.getObject() == null ? "" : (model.getObject() == true ? "Yes" : "No"); }
    @Override public Class<Boolean> getDefiniteType() { return Boolean.class; }
    @Override protected String getCustomRightClass() { return "custom-control custom-checkbox"; }
    @Override protected String getControlLblClass() { return "custom-control-label"; }
    @Override
    protected String getInnerHtml() {
        return "<input type=\"checkbox\" class=\"custom-control-input wf-checkbox-field\" wicket:id=\"fieldInput\" />";
    }

    @Override
    protected void initDisabledField() {
        if(!hasDisabledLbl()) {
            fieldInput = new CheckBox("fieldInput", model);
            fieldInput.setEnabled(false);
            addOrReplace(fieldInput.setOutputMarkupId(true));
        }
    }

	@Override
	public CheckBox getField() {
		return fieldInput;
	}

	
}
