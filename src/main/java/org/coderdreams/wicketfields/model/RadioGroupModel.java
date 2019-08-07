package org.coderdreams.wicketfields.model;

import java.io.Serializable;

public final class RadioGroupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final Integer val;
	private final String label;

	public RadioGroupModel(Integer val, String label) {
		this.val = val;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public Integer getVal() {
		return val;
	}

}
