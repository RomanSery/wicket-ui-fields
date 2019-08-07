package org.coderdreams.wicketfields.util;

import java.time.LocalTime;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.DateService;

public class LocalTimeConverter implements IConverter<LocalTime> {

    private static final long serialVersionUID = 1L;

    private final DateService dateService;

    public LocalTimeConverter(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public LocalTime convertToObject(String value, Locale locale) {
        return dateService.parseLocalTime(value);
    }

    @Override
    public String convertToString(LocalTime value, Locale locale) {
        return dateService.format(value);
    }


}
