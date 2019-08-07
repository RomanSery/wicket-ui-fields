package org.coderdreams.wicketfields;


import org.apache.wicket.ajax.AjaxRequestTarget;

public interface IAjaxUiField {
    default void onFieldChanged(AjaxRequestTarget target) {
        //no op by default
    }
}
