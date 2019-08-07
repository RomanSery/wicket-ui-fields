package org.coderdreams.wicketfields.fields.dates.internal;

import java.time.LocalDateTime;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.convert.IConverter;

public class LocalDateTimePickerControl extends Panel {
	private static final long serialVersionUID = 1L;
	private LocalDateTimeTextField myDate;

	public LocalDateTimePickerControl(String id, IModel<LocalDateTime> myDateModel, String myLabel, IConverter<LocalDateTime> converter) {
		super(id);

		myDate = new LocalDateTimeTextField("myDate", myDateModel, converter);
		myDate.setOutputMarkupId(true);
		myDate.setLabel(new Model<String>(myLabel));		
		add(myDate);
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