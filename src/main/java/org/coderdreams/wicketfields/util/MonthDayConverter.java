package org.coderdreams.wicketfields.util;


import java.time.MonthDay;
import java.util.Locale;

import org.apache.wicket.util.convert.IConverter;

public class MonthDayConverter implements IConverter<MonthDay> {
    private static final long serialVersionUID = 1L;
    @Override
    public MonthDay convertToObject(String value, Locale locale) {
        return DateUtils.parseMonthDay(value);
    }
    @Override
    public String convertToString(MonthDay value, Locale locale) {
        return DateUtils.format(value);
    }
}
