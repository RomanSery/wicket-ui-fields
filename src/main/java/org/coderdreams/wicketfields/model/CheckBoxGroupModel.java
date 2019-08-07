package org.coderdreams.wicketfields.model;

import java.io.Serializable;

import org.apache.wicket.model.IModel;

public final class CheckBoxGroupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private final IModel<Boolean> model;
	private final String label;

	public CheckBoxGroupModel(IModel<Boolean> model, String label) {
		this.model = model;
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	public IModel<Boolean> getModel() {
		return model;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckBoxGroupModel other = (CheckBoxGroupModel) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

}
