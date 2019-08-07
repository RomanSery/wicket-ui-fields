package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalTime;

import org.coderdreams.wicketfields.DateService;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.internal.LocalTimeTextField;
import org.coderdreams.wicketfields.internal.TimePickerControl;

public class TimeField extends BaseUiField<LocalTime> {
	
	private static final long serialVersionUID = 1L;	

	private DateService dateService;
	
	private TimePickerControl fieldInput;

    public TimeField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		
		fieldInput = new TimePickerControl("fieldInput", model, false, fieldLabel, dateService);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

	@Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? dateService.format(model.getObject()) : ""; }
    @Override public Class<LocalTime> getDefiniteType() { return LocalTime.class; }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" wicket:id=\"fieldInput\" style=\"padding-left:0px;\"></div>";
    }

	@Override
	public LocalTimeTextField getField() {
		return fieldInput.getMyDate();
	}

	
}
