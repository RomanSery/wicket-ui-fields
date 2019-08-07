package org.coderdreams.wicketfields.fields.bool;


import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;

import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxYesNoUnknownField extends YesNoUnknownField implements IAjaxUiField {
	private static final long serialVersionUID = 1L;

    public AjaxYesNoUnknownField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		super.initField();

        getField().add(new AjaxFormChoiceComponentUpdatingBehavior() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {				
				onFieldChanged(target);
                clientScripts(target);
			}
		});
	}
}
