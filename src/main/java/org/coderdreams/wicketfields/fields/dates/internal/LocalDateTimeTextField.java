package org.coderdreams.wicketfields.fields.dates.internal;

import java.time.LocalDateTime;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class LocalDateTimeTextField extends TextField<LocalDateTime> implements ITextFormatProvider {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PATTERN = "MM/dd/yyyy h:mm a";
	private final IConverter<LocalDateTime> converter;

	LocalDateTimeTextField(final String id, final IModel<LocalDateTime> model, IConverter<LocalDateTime> converter) {
		super(id, model, LocalDateTime.class);
		this.converter = converter;
	}

	@Override
	protected IConverter<?> createConverter(Class<?> type) {
		if (LocalDateTime.class.isAssignableFrom(type)) {
			return converter;
		}
		return null;
	}

	@Override
	public String getTextFormat()
	{
		return DEFAULT_PATTERN;
	}

	@Override
	protected String[] getInputTypes() {
		return new String[] { "text", "date", "datetime", "datetime-local", "month", "time", "week" };
	}
}
