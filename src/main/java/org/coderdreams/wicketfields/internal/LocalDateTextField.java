package org.coderdreams.wicketfields.internal;

import java.time.LocalDate;

import org.apache.wicket.markup.html.form.AbstractTextComponent.ITextFormatProvider;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.convert.IConverter;

import org.coderdreams.wicketfields.util.LocalDateConverter;

public class LocalDateTextField extends TextField<LocalDate> implements ITextFormatProvider
{

	private static final long serialVersionUID = 1L;

	private static final String DEFAULT_PATTERN = "MM/dd/yyyy";

	/**
	 * The date pattern of the text field
	 */
	private String datePattern = null;

	/**
	 * The converter for the TextField
	 */
	private final IConverter<LocalDate> converter;

	/**
	 * Creates a new DateTextField, without a specified pattern. This is the same as calling
	 * <code>new TextField(id, Date.class)</code>
	 * 
	 * @param id
	 *            The id of the text field
	 * 
	 * @see TextField
	 */
	public LocalDateTextField(final String id)
	{
		this(id, null, DEFAULT_PATTERN);
	}

	/**
	 * Creates a new DateTextField, without a specified pattern. This is the same as calling
	 * <code>new TextField(id, object, Date.class)</code>
	 *
	 * @param id
	 *            The id of the text field
	 * @param model
	 *            The model
	 *
	 * @see TextField
	 */
	public LocalDateTextField(final String id, final IModel<LocalDate> model)
	{
		this(id, model, DEFAULT_PATTERN);
	}


	/**
	 * Creates a new DateTextField bound with a specific <code>SimpleDateFormat</code> pattern.
	 *
	 * @param id
	 *            The id of the text field
	 * @param model
	 *            The model
	 * @param datePattern
	 *            A <code>SimpleDateFormat</code> pattern
	 *
	 * @see TextField
	 */
	public LocalDateTextField(final String id, final IModel<LocalDate> model, final String datePattern)
	{
		super(id, model, LocalDate.class);
		this.datePattern = datePattern;
		converter = new LocalDateConverter();

	}

	/**
	 * Returns the default converter if created without pattern; otherwise it returns a
	 * pattern-specific converter.
	 *
	 * @param type
	 *            The type for which the convertor should work
	 *
	 * @return A pattern-specific converter
	 *
	 * @see TextField
	 */
	@Override
	protected IConverter<?> createConverter(Class<?> type)
	{
		if (LocalDate.class.isAssignableFrom(type))
		{
			return converter;
		}
		return null;
	}

	/**
	 * Returns the date pattern.
	 *
	 * @see ITextFormatProvider#getTextFormat()
	 */
	@Override
	public String getTextFormat()
	{
		return datePattern;
	}

	

	@Override
	protected String[] getInputTypes()
	{
		return new String[] { "text", "date", "datetime", "datetime-local", "month", "time", "week" };
	}
}
