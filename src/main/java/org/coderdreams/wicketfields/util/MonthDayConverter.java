package org.coderdreams.wicketfields.util;


import java.time.MonthDay;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.DateService;

public class MonthDayConverter implements IConverter<MonthDay> {

    private static final long serialVersionUID = 1L;

    private final DateService dateService;

    public MonthDayConverter(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public MonthDay convertToObject(String value, Locale locale) {
        return dateService.parseMonthDay(value);
    }

    @Override
    public String convertToString(MonthDay value, Locale locale) {
        return dateService.format(value);
    }


}
