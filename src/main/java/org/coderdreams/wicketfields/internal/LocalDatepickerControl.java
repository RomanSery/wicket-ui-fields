package org.coderdreams.wicketfields.internal;


import java.time.LocalDate;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class LocalDatepickerControl extends Panel {

	private static final long serialVersionUID = 1L;

	private LocalDateTextField myDate = null;
	private boolean isRequired = false;
	
	public LocalDatepickerControl(String id, IModel<LocalDate> myDateModel, boolean isRequired, String myLabel) {
		this(id, myDateModel, isRequired, Model.of(myLabel));
	}
	
	public LocalDatepickerControl(String id, IModel<LocalDate> myDateModel, boolean isRequired, IModel<String> myLabelModel) {
		super(id);

		this.isRequired = isRequired;
		myDate = new LocalDateTextField("myDate", myDateModel, "MM/dd/yyyy") {			
			private static final long serialVersionUID = 1L;
            @Override
			public boolean isRequired() { return customIsRequired(); }
		};
		myDate.setRequired(isRequired).setOutputMarkupId(true);
		myDate.setLabel(myLabelModel);
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
	
	public LocalDateTextField getMyDate() {
		return myDate;
	}
	
	@Override
	public void onConfigure() {
        super.onConfigure();
		this.setOutputMarkupId(true);
	}
}