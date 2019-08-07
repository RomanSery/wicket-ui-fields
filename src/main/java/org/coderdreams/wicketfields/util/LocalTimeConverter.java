package org.coderdreams.wicketfields.util;

import java.time.LocalTime;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

public class LocalTimeConverter implements IConverter<LocalTime> {
    private static final long serialVersionUID = 1L;
    @Override
    public LocalTime convertToObject(String value, Locale locale) {
        return DateUtils.parseLocalTime(value);
    }
    @Override
    public String convertToString(LocalTime value, Locale locale) {
        return DateUtils.format(value);
    }
}
