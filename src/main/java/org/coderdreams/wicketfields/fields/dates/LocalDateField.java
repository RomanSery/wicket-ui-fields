package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalDate;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.LocalDateTextField;
import org.coderdreams.wicketfields.fields.dates.internal.LocalDatepickerControl;
import org.coderdreams.wicketfields.util.DateUtils;

public class LocalDateField extends BaseUiField<LocalDate> {
	private static final long serialVersionUID = 1L;	

	private LocalDatepickerControl fieldInput;

    public LocalDateField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
		
		fieldInput = new LocalDatepickerControl("fieldInput", model, fieldLabel);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override protected Class getDefiniteType() { return LocalDate.class; }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" wicket:id=\"fieldInput\" style=\"padding-left:0px;\"></div> ";
    }

	@Override
	public LocalDateTextField getField() {
		return fieldInput.getMyDate();
	}
	
	protected LocalDatepickerControl getDatePicker() {
		return fieldInput;
	}
	
}
