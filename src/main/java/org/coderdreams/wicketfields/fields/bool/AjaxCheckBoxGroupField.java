package org.coderdreams.wicketfields.fields.bool;

import org.coderdreams.wicketfields.FieldArgs;

public class AjaxCheckBoxGroupField extends CheckBoxGroupField {
	private static final long serialVersionUID = 1L;

    public AjaxCheckBoxGroupField(FieldArgs args) {
        super(args);
    }

    @Override protected boolean isAjax() { return true; }
}
