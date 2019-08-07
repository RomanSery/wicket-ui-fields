package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalDateTime;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.LocalDateTimePickerControl;
import org.coderdreams.wicketfields.fields.dates.internal.LocalDateTimeTextField;
import org.coderdreams.wicketfields.util.DateUtils;
import org.coderdreams.wicketfields.util.LocalDateTimeConverter;

public class LocalDateTimeField extends BaseUiField<LocalDateTime> {
	private static final long serialVersionUID = 1L;
	private LocalDateTimePickerControl fieldInput;

    public LocalDateTimeField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
		fieldInput = new LocalDateTimePickerControl("fieldInput", model, fieldLabel, getConverter());
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

	protected IConverter<LocalDateTime> getConverter() {
        return new LocalDateTimeConverter();
    }

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
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
