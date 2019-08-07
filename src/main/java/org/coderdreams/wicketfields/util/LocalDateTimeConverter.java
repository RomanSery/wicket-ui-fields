package org.coderdreams.wicketfields.util;

import java.time.LocalDateTime;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

public class LocalDateTimeConverter implements IConverter<LocalDateTime> {
    private static final long serialVersionUID = 1L;
    @Override
    public LocalDateTime convertToObject(String value, Locale locale) {
        return DateUtils.parse2(value);
    }

    @Override
    public String convertToString(LocalDateTime value, Locale locale) {
        return DateUtils.format(value);
    }
}
