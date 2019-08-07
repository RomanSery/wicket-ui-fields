package org.coderdreams.wicketfields.internal;

import java.time.LocalDateTime;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.DateService;

import org.coderdreams.wicketfields.util.LocalDateTimeConverter;

public class LocalDateTimeTextField extends TextField<LocalDateTime> implements ITextFormatProvider
{

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_PATTERN = "MM/dd/yyyy h:mm a";

	private final IConverter<LocalDateTime> converter;


	public LocalDateTimeTextField(final String id, final IModel<LocalDateTime> model, DateService dateService)
	{
		super(id, model, LocalDateTime.class);
		converter = new LocalDateTimeConverter(dateService);
	}


	@Override
	protected IConverter<?> createConverter(Class<?> type)
	{
		if (LocalDateTime.class.isAssignableFrom(type))
		{
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
	protected String[] getInputTypes()
	{
		return new String[] { "text", "date", "datetime", "datetime-local", "month", "time", "week" };
	}
}
