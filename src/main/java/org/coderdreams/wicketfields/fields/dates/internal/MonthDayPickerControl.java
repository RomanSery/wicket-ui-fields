package org.coderdreams.wicketfields.fields.dates.internal;


import java.time.MonthDay;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

public class MonthDayPickerControl extends Panel {
	private static final long serialVersionUID = 1L;
	private MonthDayTextField myDate;

	public MonthDayPickerControl(String id, IModel<MonthDay> myDateModel, String myLabel, final IConverter<MonthDay> converter) {
		super(id);

		myDate = new MonthDayTextField("myDate", myDateModel, converter);
		myDate.setOutputMarkupId(true);
		myDate.setLabel(new Model<String>(myLabel));		
		add(myDate);
	}
	
	public MonthDayTextField getMyDate() {
		return myDate;
	}
	
	@Override
	public void onConfigure() {
        super.onConfigure();
		this.setOutputMarkupId(true);
	}
}