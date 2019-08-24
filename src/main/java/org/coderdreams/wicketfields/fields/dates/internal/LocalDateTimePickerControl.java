package org.coderdreams.wicketfields.fields.dates.internal;

import java.time.LocalDateTime;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

public class LocalDateTimePickerControl extends Panel {
	private static final long serialVersionUID = 1L;

	private final String parentMarkupId;
	private LocalDateTimeTextField myDate;

	public LocalDateTimePickerControl(String id, IModel<LocalDateTime> myDateModel, String myLabel, IConverter<LocalDateTime> converter, String parentMarkupId) {
		super(id);
		this.parentMarkupId = parentMarkupId;

		WebMarkupContainer dateCont = new WebMarkupContainer("dateCont");
		add(dateCont.setOutputMarkupId(true));

		WebMarkupContainer appendCont = new WebMarkupContainer("appendCont") {
			@Override
			protected void onComponentTag(final ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("data-target", "#" + dateCont.getMarkupId());
			}
		};
		dateCont.add(appendCont);

		myDate = new LocalDateTimeTextField("myDate", myDateModel, converter) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onComponentTag(final ComponentTag tag) {
				super.onComponentTag(tag);
				tag.put("data-target", "#" + dateCont.getMarkupId());
			}
		};
		myDate.setOutputMarkupId(true);
		myDate.setLabel(new Model<String>(myLabel));
		dateCont.add(myDate);
	}

	public LocalDateTimeTextField getMyDate() {
		return myDate;
	}
	
	@Override
	public void onConfigure() {
        super.onConfigure();
		this.setOutputMarkupId(true);
	}
}