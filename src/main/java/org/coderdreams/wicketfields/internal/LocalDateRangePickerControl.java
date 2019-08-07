package org.coderdreams.wicketfields.internal;

import java.time.LocalDate;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.model.LocalDateRangeModel;

public class LocalDateRangePickerControl extends Panel {

	private static final long serialVersionUID = 1L;

	private LocalDateTextField myFromDate = null;
	private LocalDateTextField myToDate = null;
	private boolean isRequired = false;
	
	public LocalDateRangePickerControl(String id, IModel<LocalDate> myDateFromModel, IModel<LocalDate> myDateToModel, boolean isRequired, String myLabel) {
		super(id);

		this.isRequired = isRequired;
		myFromDate = new LocalDateTextField("fromDate", myDateFromModel, "MM/dd/yyyy") {			
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myFromDate.setRequired(isRequired).setOutputMarkupId(true);
		myFromDate.setLabel(new Model<String>(myLabel));						
		add(myFromDate);	
		
		myToDate = new LocalDateTextField("toDate", myDateToModel, "MM/dd/yyyy") {			
			private static final long serialVersionUID = 1L;
            @Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myToDate.setRequired(isRequired).setOutputMarkupId(true);
		myToDate.setLabel(new Model<String>(myLabel));						
		add(myToDate);

	}
	
	
	
	public LocalDateRangePickerControl(String id, LocalDateRangeModel dateRangeModel, boolean isRequired, String myLabel) {
		super(id);

		this.isRequired = isRequired;
		myFromDate = new LocalDateTextField("fromDate", dateRangeModel.getStartDateModel(), "MM/dd/yyyy") {			
			private static final long serialVersionUID = 1L;
            @Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myFromDate.setRequired(isRequired).setOutputMarkupId(true);
		myFromDate.setLabel(new Model<String>(myLabel));						
		add(myFromDate);	
		
		myToDate = new LocalDateTextField("toDate", dateRangeModel.getEndDateModel(), "MM/dd/yyyy") {			
			private static final long serialVersionUID = 1L;
            @Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myToDate.setRequired(isRequired).setOutputMarkupId(true);
		myToDate.setLabel(new Model<String>(myLabel));						
		add(myToDate);

	}
	
	
	
	/*
	 * This method may be over-ridden, if there is a need for a more dynamic determination of whether the date field is
	 * required, beyond simply using the statically determined isRequired parameter.
	 */
	public boolean customIsRequired() {
		return this.isRequired;
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