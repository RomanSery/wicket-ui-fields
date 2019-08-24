package org.coderdreams.wicketfields.fields.dates.internal;

import java.time.LocalDate;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.util.LocalDateConverter;

public class LocalDateTextField extends TextField<LocalDate> implements ITextFormatProvider {
	private static final long serialVersionUID = 1L;
	private final String datePattern;
	private final IConverter<LocalDate> converter;

	public LocalDateTextField(final String id, final IModel<LocalDate> model, final String datePattern) {
		super(id, model, LocalDate.class);
		this.datePattern = datePattern;
		converter = new LocalDateConverter();
	}

	@Override
	protected IConverter<?> createConverter(Class<?> type) {
		if (LocalDate.class.isAssignableFrom(type)) {
			return converter;
		}
		return null;
	}

	@Override
	public String getTextFormat()
	{
		return datePattern;
	}

	@Override
	protected String[] getInputTypes() {
		return new String[] { "text", "date", "datetime", "datetime-local", "month", "time", "week" };
	}
}
