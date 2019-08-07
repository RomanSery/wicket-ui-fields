package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxTextAreaWithCounterField extends TextAreaWithCounterField implements IAjaxUiField {
	private static final long serialVersionUID = 1L;

    public AjaxTextAreaWithCounterField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
	    super.initField();
        getField().add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onFieldChanged(target);
			}
		});
	}
}
