package org.coderdreams.wicketfields.event;

import java.io.Serializable;

import org.apache.wicket.ajax.AjaxRequestTarget;

public class InitPanelFieldsEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient final AjaxRequestTarget target;

	public InitPanelFieldsEvent(AjaxRequestTarget target) {
		this.target = target;
	}

	public AjaxRequestTarget getAjaxRequestTarget() {
		return target;
	}
}
