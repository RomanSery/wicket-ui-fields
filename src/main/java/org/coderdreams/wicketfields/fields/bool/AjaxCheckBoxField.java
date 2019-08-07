package org.coderdreams.wicketfields.fields.bool;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxCheckBoxField extends CheckBoxField implements IAjaxUiField {
	
	private static final long serialVersionUID = 1L;

    public AjaxCheckBoxField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
	    super.initField();

        getField().add(new AjaxFormComponentUpdatingBehavior("click") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if(getField().getModelObject() != null && BooleanUtils.isTrue(getField().getModelObject())) {
					onFieldChanged(target);
				} else {
					onFieldChanged(target);
				}
                clientScripts(target);
				
			}
		});
	}
}
