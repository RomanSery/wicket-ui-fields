package org.coderdreams.wicketfields.internal;


import java.time.MonthDay;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.DateService;

public class MonthDayPickerControl extends Panel {

	private static final long serialVersionUID = 1L;

	private MonthDayTextField myDate = null;
	private boolean isRequired = false;
	
	public MonthDayPickerControl(String id, IModel<MonthDay> myDateModel, boolean isRequired, String myLabel, DateService dateService) {
		super(id);

		this.isRequired = isRequired;
		myDate = new MonthDayTextField("myDate", myDateModel, dateService) {
			private static final long serialVersionUID = 1L;
            @Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myDate.setRequired(isRequired).setOutputMarkupId(true);
		myDate.setLabel(new Model<String>(myLabel));		
		add(myDate);	

	}

	/*
	 * This method may be over-ridden, if there is a need for a more dynamic determination of whether the date field is
	 * required, beyond simply using the statically determined isRequired parameter.
	 */
	public boolean customIsRequired() {
		return this.isRequired;
	}
	
	public void setDatePickerEnabled(boolean isEnabled) {
		myDate.setEnabled(isEnabled);
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