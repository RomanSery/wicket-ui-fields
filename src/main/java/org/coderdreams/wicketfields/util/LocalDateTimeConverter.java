package org.coderdreams.wicketfields.util;

import java.time.LocalDateTime;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.DateService;

public class LocalDateTimeConverter implements IConverter<LocalDateTime> {

    private static final long serialVersionUID = 1L;

    private final DateService dateService;

    public LocalDateTimeConverter(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public LocalDateTime convertToObject(String value, Locale locale) {
        return dateService.parse2(value);
    }

    @Override
    public String convertToString(LocalDateTime value, Locale locale) {
        return dateService.format(value, false);
    }


}
