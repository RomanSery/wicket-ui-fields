package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalTime;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.LocalTimeTextField;
import org.coderdreams.wicketfields.util.DateUtils;
import org.coderdreams.wicketfields.util.LocalTimeConverter;

public class TimeField extends BaseUiField<LocalTime> {
	private static final long serialVersionUID = 1L;
	private LocalTimeTextField fieldInput;

    public TimeField(FieldArgs args) {
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

		fieldInput = new LocalTimeTextField("fieldInput", model, getConverter()) {
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

	protected IConverter<LocalTime> getConverter() {
    	return new LocalTimeConverter();
	}
	@Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override public Class<LocalTime> getDefiniteType() { return LocalTime.class; }
    @Override
    protected String getInnerHtml() {
		return
			"<div class=\"input-group date date-time-only-picker wf-time-field\" data-target-input=\"nearest\" wicket:id=\"dateCont\">" +
				"<input type=\"text\" wicket:id=\"fieldInput\" class=\"form-control datetimepicker-input input-mask-date-time\" />" +
				"<div class=\"input-group-append\" data-toggle=\"datetimepicker\" wicket:id=\"appendCont\">" +
				"<div class=\"input-group-text\"><i class=\"fa fa-clock-o\"></i></div>" +
				"</div>" +
			"</div>";
    }

	@Override
	public LocalTimeTextField getField() {
		return fieldInput;
	}

	
}
