package org.coderdreams.wicketfields.fields.dates.internal;

import java.time.LocalTime;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

public class LocalTimeTextField extends TextField<LocalTime> implements ITextFormatProvider {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PATTERN = "hh:mm a";
	private final IConverter<LocalTime> converter;

	LocalTimeTextField(final String id, final IModel<LocalTime> model, IConverter<LocalTime> converter) {
		super(id, model, LocalTime.class);
		this.converter = converter;
	}

	@Override
	protected IConverter<?> createConverter(Class<?> type) {
		if (LocalTime.class.isAssignableFrom(type)) {
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
