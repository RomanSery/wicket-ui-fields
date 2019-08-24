package org.coderdreams.wicketfields.fields.dates;


import java.time.MonthDay;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.MonthDayTextField;
import org.coderdreams.wicketfields.util.DateUtils;
import org.coderdreams.wicketfields.util.MonthDayConverter;

public class MonthDayField extends BaseUiField<MonthDay> {
	private static final long serialVersionUID = 1L;
	private MonthDayTextField fieldInput;

    public MonthDayField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {

        WebMarkupContainer dateCont = new WebMarkupContainer("dateCont");
        addOrReplace(dateCont.setOutputMarkupId(true));

        WebMarkupContainer appendCont = new WebMarkupContainer("appendCont") {
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("data-target", "#" + dateCont.getMarkupId());
            }
        };
        dateCont.addOrReplace(appendCont);

        fieldInput = new MonthDayTextField("fieldInput", model, getConverter()) {
            private static final long serialVersionUID = 1L;
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("data-target", "#" + dateCont.getMarkupId());
            }
        };
        fieldInput.setOutputMarkupId(true);
        fieldInput.setLabel(new Model<String>(fieldLabel));
        dateCont.addOrReplace(fieldInput);
	}

	protected IConverter<MonthDay> getConverter() {
        return new MonthDayConverter();
    }
    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override public Class<MonthDay> getDefiniteType() { return MonthDay.class; }
    @Override
    protected String getInnerHtml() {
        return
                "<div class=\"input-group date date-month-only-picker wf-monthday-field\" data-target-input=\"nearest\" wicket:id=\"dateCont\">" +
                        "<input type=\"text\" wicket:id=\"fieldInput\" class=\"form-control datetimepicker-input input-mask-date-time\" />" +
                        "<div class=\"input-group-append\" data-toggle=\"datetimepicker\" wicket:id=\"appendCont\">" +
                        "<div class=\"input-group-text\"><i class=\"fa fa-calendar\"></i></div>" +
                        "</div>" +
                        "</div>";
    }
	@Override
	public MonthDayTextField getField() {
		return fieldInput;
	}
}
