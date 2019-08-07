package org.coderdreams.wicketfields.fields.dates;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;

import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxTimeField extends TimeField implements IAjaxUiField {
	private static final long serialVersionUID = 1L;	

    public AjaxTimeField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		super.initField();

        getField().add(new AjaxFormComponentUpdatingBehavior("dp.change") {
			private static final long serialVersionUID = 1L;			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onFieldChanged(target);
			}
		});
	}
}
