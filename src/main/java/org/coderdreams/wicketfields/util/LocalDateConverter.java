package org.coderdreams.wicketfields.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.lang.Args;

public class LocalDateConverter implements IConverter<LocalDate> {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_PATTERN = "MM/dd/yyyy";

    private final String pattern;

    public LocalDateConverter() {
        this(DEFAULT_PATTERN);
    }

    public static LocalDate convert(String str) {
        try {
            return LocalDate.parse(str, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
        } catch (Exception e) {
            return null;
        }
    }

    public LocalDateConverter(String pattern) {
        this.pattern = Args.notNull(pattern, "Pattern");
    }

    private DateTimeFormatter getFormatter() {
        return DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    public LocalDate convertToObject(String value, Locale locale) {
        try {
            return LocalDate.parse(value, getFormatter());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String convertToString(LocalDate value, Locale locale) {
        return value == null ? "" : getFormatter().format(value);
    }


}
