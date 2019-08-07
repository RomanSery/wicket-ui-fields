package org.coderdreams.wicketfields.fields.dates.internal;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.model.LocalDateRangeModel;

public class LocalDateRangePickerControl extends Panel {
	private static final long serialVersionUID = 1L;
	private LocalDateTextField myFromDate;
	private LocalDateTextField myToDate;

	public LocalDateRangePickerControl(String id, LocalDateRangeModel dateRangeModel, String myLabel) {
		super(id);

		myFromDate = new LocalDateTextField("fromDate", dateRangeModel.getStartDateModel(), "MM/dd/yyyy");
		myFromDate.setOutputMarkupId(true);
		myFromDate.setLabel(new Model<String>(myLabel));						
		add(myFromDate);	
		
		myToDate = new LocalDateTextField("toDate", dateRangeModel.getEndDateModel(), "MM/dd/yyyy");
		myToDate.setOutputMarkupId(true);
		myToDate.setLabel(new Model<String>(myLabel));						
		add(myToDate);

	}

	public void setDatePickerEnabled(boolean isEnabled) {
		myToDate.setEnabled(isEnabled);
		myFromDate.setEnabled(isEnabled);
	}
	
	@Override
	public void onConfigure() {
        super.onConfigure();
		this.setOutputMarkupId(true);
	}
	
}