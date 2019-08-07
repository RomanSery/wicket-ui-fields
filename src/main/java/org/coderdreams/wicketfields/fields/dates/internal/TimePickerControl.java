package org.coderdreams.wicketfields.fields.dates.internal;


import java.time.LocalTime;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

public class TimePickerControl extends Panel {
	private static final long serialVersionUID = 1L;
	private LocalTimeTextField myDate;
	
	public TimePickerControl(String id, IModel<LocalTime> myDateModel, String myLabel, IConverter<LocalTime> converter) {
		super(id);

		myDate = new LocalTimeTextField("myDate", myDateModel, converter);
		myDate.setOutputMarkupId(true);
		myDate.setLabel(new Model<String>(myLabel));		
		add(myDate);
	}
	
	public LocalTimeTextField getMyDate() {
		return myDate;
	}
	
	@Override
	public void onConfigure() {
        super.onConfigure();
		this.setOutputMarkupId(true);
	}
}