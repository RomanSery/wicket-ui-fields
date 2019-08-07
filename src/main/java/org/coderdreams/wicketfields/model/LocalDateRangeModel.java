package org.coderdreams.wicketfields.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.apache.wicket.model.IModel;


public final class LocalDateRangeModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final IModel<LocalDate> startDateModel;
	private final IModel<LocalDate> endDateModel;

	public LocalDateRangeModel(IModel<LocalDate> startDateModel, IModel<LocalDate> endDateModel) {
		super();
		this.startDateModel = startDateModel;
		this.endDateModel = endDateModel;
	}

	public IModel<LocalDate> getStartDateModel() {
		return startDateModel;
	}

	public IModel<LocalDate> getEndDateModel() {
		return endDateModel;
	}

}
