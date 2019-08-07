package org.coderdreams.wicketfields.fields.dates;


import java.time.MonthDay;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.MonthDayPickerControl;
import org.coderdreams.wicketfields.fields.dates.internal.MonthDayTextField;
import org.coderdreams.wicketfields.util.DateUtils;
import org.coderdreams.wicketfields.util.MonthDayConverter;

public class MonthDayField extends BaseUiField<MonthDay> {
	private static final long serialVersionUID = 1L;
	private MonthDayPickerControl fieldInput;

    public MonthDayField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		fieldInput = new MonthDayPickerControl("fieldInput", model, fieldLabel, getConverter());
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

	protected IConverter<MonthDay> getConverter() {
        return new MonthDayConverter();
    }

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override public Class<MonthDay> getDefiniteType() { return MonthDay.class; }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" wicket:id=\"fieldInput\" style=\"padding-left:0px;\"></div>";
    }
	@Override
	public MonthDayTextField getField() {
		return fieldInput.getMyDate();
	}
}
