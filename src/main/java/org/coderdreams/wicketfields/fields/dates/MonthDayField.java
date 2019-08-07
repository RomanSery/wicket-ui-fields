package org.coderdreams.wicketfields.fields.dates;


import java.time.MonthDay;

import org.coderdreams.wicketfields.DateService;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.internal.MonthDayTextField;
import org.coderdreams.wicketfields.internal.MonthDayPickerControl;

public class MonthDayField extends BaseUiField<MonthDay> {
	private static final long serialVersionUID = 1L;
	private DateService dateService;
	
	private MonthDayPickerControl fieldInput;

    public MonthDayField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {
		fieldInput = new MonthDayPickerControl("fieldInput", model, false, fieldLabel, dateService);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? dateService.format(model.getObject()) : ""; }
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
