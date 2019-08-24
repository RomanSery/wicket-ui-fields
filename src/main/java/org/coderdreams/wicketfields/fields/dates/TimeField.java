package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalTime;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.LocalTimeTextField;
import org.coderdreams.wicketfields.fields.dates.internal.TimePickerControl;
import org.coderdreams.wicketfields.util.DateUtils;
import org.coderdreams.wicketfields.util.LocalTimeConverter;

public class TimeField extends BaseUiField<LocalTime> {
	private static final long serialVersionUID = 1L;
	private TimePickerControl fieldInput;

    public TimeField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		
		fieldInput = new TimePickerControl("fieldInput", model, fieldLabel, getConverter());
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

	protected IConverter<LocalTime> getConverter() {
    	return new LocalTimeConverter();
	}

	@Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override public Class<LocalTime> getDefiniteType() { return LocalTime.class; }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"wf-time-field\" wicket:id=\"fieldInput\"></div>";
    }

	@Override
	public LocalTimeTextField getField() {
		return fieldInput.getMyDate();
	}

	
}
