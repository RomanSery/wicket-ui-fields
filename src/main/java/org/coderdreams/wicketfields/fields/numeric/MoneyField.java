package org.coderdreams.wicketfields.fields.numeric;

import java.math.BigDecimal;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.util.MoneyConverter;


public class MoneyField extends BaseUiField<BigDecimal> {
	private static final long serialVersionUID = 1L;

    private TextField<BigDecimal> fieldInput;

    private static final IConverter<BigDecimal> converter = new MoneyConverter();

    public MoneyField(FieldArgs args) {
        super(args);
    }

    @Override public Class<BigDecimal> getDefiniteType() { return BigDecimal.class; }
    protected boolean allowNegative() { return false; }

    @Override
    protected void initField() {
        fieldInput = new TextField<BigDecimal>("fieldInput", model, BigDecimal.class) {
            private static final long serialVersionUID = 1L;
            @Override
            protected IConverter<BigDecimal> createConverter(Class<?> type) {
                if (BigDecimal.class.isAssignableFrom(type)) {
                    return converter;
                }
                return null;
            }
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("class", allowNegative() ? "col-xs-10 dollar-amt-s-allow-neg" : "col-xs-10 dollar-amt-s");
            }
        };

        fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
    }

    @Override
    protected String getInnerHtml() {
        return "<input type=\"text\" wicket:id=\"fieldInput\" class=\"col-xs-10 wf-money-field\" />";
    }

    @Override
    public TextField<BigDecimal> getField() {
        return fieldInput;
    }

}
