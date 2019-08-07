package org.coderdreams.wicketfields.internal;


import java.time.MonthDay;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.DateService;

import org.coderdreams.wicketfields.util.MonthDayConverter;

public class MonthDayTextField extends TextField<MonthDay> implements ITextFormatProvider
{

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_PATTERN = "MM/dd";

	private final IConverter<MonthDay> converter;


	public MonthDayTextField(final String id, final IModel<MonthDay> model, final DateService dateService)
	{
		super(id, model, MonthDay.class);
		this.converter = new MonthDayConverter(dateService);
	}

	@Override
	protected IConverter<?> createConverter(Class<?> type)
	{
		if (MonthDay.class.isAssignableFrom(type))
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
