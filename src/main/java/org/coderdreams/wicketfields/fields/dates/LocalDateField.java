package org.coderdreams.wicketfields.fields.dates;

import java.time.LocalDate;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.fields.dates.internal.LocalDateTextField;
import org.coderdreams.wicketfields.util.DateUtils;

public class LocalDateField extends BaseUiField<LocalDate> {
	private static final long serialVersionUID = 1L;	

	private LocalDateTextField fieldInput;

    public LocalDateField(FieldArgs args) {
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

		fieldInput = new LocalDateTextField("fieldInput", model, "MM/dd/yyyy") {
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

    @Override protected String getDisabledLbl() { return model != null && model.getObject() != null ? DateUtils.format(model.getObject()) : ""; }
    @Override protected Class getDefiniteType() { return LocalDate.class; }
    @Override
    protected String getInnerHtml() {
		return
		"<div class=\"input-group date date-picker wf-localdate-field\" data-target-input=\"nearest\" wicket:id=\"dateCont\">" +
			"<input type=\"text\" wicket:id=\"fieldInput\" class=\"form-control datetimepicker-input input-mask-date-time\" />" +
			"<div class=\"input-group-append\" data-toggle=\"datetimepicker\" wicket:id=\"appendCont\">" +
			"<div class=\"input-group-text\"><i class=\"fa fa-calendar\"></i></div>" +
			"</div>" +
		"</div>";
    }

	@Override
	public LocalDateTextField getField() {
		return fieldInput;
	}

}
