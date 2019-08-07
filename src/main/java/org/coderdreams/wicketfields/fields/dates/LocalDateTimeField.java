package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalDateTime;

import org.coderdreams.wicketfields.DateService;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.internal.LocalDateTimeTextField;
import org.coderdreams.wicketfields.internal.LocalDateTimePickerControl;

public class LocalDateTimeField extends BaseUiField<LocalDateTime> {
	private static final long serialVersionUID = 1L;	

	private DateService dateService;
	private LocalDateTimePickerControl fieldInput;

    public LocalDateTimeField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
		
		fieldInput = new LocalDateTimePickerControl("fieldInput", model, false, fieldLabel, dateService);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? dateService.format(model.getObject()) : ""; }
    @Override public Class<LocalDateTime> getDefiniteType() { return LocalDateTime.class; }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" wicket:id=\"fieldInput\" style=\"padding-left:0px;\"></div> ";
    }

	@Override
	public LocalDateTimeTextField getField() {
		return fieldInput.getMyDate();
	}

	
}
