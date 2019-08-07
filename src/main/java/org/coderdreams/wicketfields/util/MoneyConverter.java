package org.coderdreams.wicketfields.util;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.util.convert.converter.AbstractDecimalConverter;

public class MoneyConverter extends AbstractDecimalConverter<BigDecimal>
{
    private static final long serialVersionUID = 1L;
    private static final String dollarSign = "$";

    @Override
    protected Class<BigDecimal> getTargetType()
    {
        return BigDecimal.class;
    }

    @Override
    public BigDecimal convertToObject(final String value, final Locale locale)
    {
        if(value != null) {
            return parse(StringUtils.replace(value, "$", ""), null, null, locale);
        }
        return null;
    }

    @Override
    public String convertToString(BigDecimal value, Locale locale) {
        if(value == null) {
            return "";
        }
        return dollarSign + super.convertToString(value, locale);

    }
}
