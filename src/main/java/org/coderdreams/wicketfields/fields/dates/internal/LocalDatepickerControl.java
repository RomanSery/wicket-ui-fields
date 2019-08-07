package org.coderdreams.wicketfields.fields.dates.internal;


import java.time.LocalDate;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class LocalDatepickerControl extends Panel {
	private static final long serialVersionUID = 1L;
	private LocalDateTextField myDate;

	public LocalDatepickerControl(String id, IModel<LocalDate> myDateModel, String myLabel) {
		this(id, myDateModel, Model.of(myLabel));
	}
	
	public LocalDatepickerControl(String id, IModel<LocalDate> myDateModel, IModel<String> myLabelModel) {
		super(id);

		myDate = new LocalDateTextField("myDate", myDateModel, "MM/dd/yyyy");
		myDate.setOutputMarkupId(true);
		myDate.setLabel(myLabelModel);
		add(myDate);
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