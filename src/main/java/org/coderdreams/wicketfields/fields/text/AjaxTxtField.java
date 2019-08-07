package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxTxtField<T> extends TxtField<T> implements IAjaxUiField {
	private static final long serialVersionUID = 1L;

    public AjaxTxtField(FieldArgs args) {
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
