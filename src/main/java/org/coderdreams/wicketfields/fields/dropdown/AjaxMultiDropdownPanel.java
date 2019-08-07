package org.coderdreams.wicketfields.fields.dropdown;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxMultiDropdownPanel<T> extends MultiDropdownField<List<T>> implements IAjaxUiField {

	private static final long serialVersionUID = 1L;	

    public AjaxMultiDropdownPanel(FieldArgs args) {
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
