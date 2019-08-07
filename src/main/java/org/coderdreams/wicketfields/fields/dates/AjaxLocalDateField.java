package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalDate;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.util.string.StringValue;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;
import org.coderdreams.wicketfields.util.LocalDateConverter;

public class AjaxLocalDateField extends LocalDateField implements IAjaxUiField {
	private static final long serialVersionUID = 1L;

    public AjaxLocalDateField(FieldArgs args) {
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

        getField().add(new AbstractDefaultAjaxBehavior() {
            private static final long serialVersionUID = 1L;
            protected void respond(final AjaxRequestTarget target) {
                StringValue date = getRequest().getRequestParameters().getParameterValue("date");
                if(date != null && !date.isNull()) {
                    LocalDate dt = LocalDateConverter.convert(date.toString());
                    if (dt.getYear() < 1000) {
                        dt = dt.plusYears(2000);
                    }
                    AjaxLocalDateField.this.model.setObject(dt);
                    getDatePicker().modelChanged();
                    getDatePicker().getMyDate().modelChanged();
                    onFieldChanged(target);
                }
            }
        });
	}
}
