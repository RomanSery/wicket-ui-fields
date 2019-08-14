package org.coderdreams.wicketfields.fields.bool;

import org.coderdreams.wicketfields.FieldArgs;

public class CheckBoxReverseField extends CheckBoxField {
	private static final long serialVersionUID = 1L;

    public CheckBoxReverseField(FieldArgs args) {
        super(args);
    }

    @Override
    protected String getBaseMarkup() {
	    return "<wicket:panel>" +
                "<div class=\"form-group wf-checkbox-reverse-field\"><label class=\"control-label col-sm-{LCLASS}\"> {INNERHTML} {DISABLEDHTML} </label><div class=\"{RCLASS}\"> <span wicket:id=\"fieldLabel\"></span> </div></div>" +
                "</wicket:panel>";
	}
}
