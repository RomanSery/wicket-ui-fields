package org.coderdreams;

import org.apache.wicket.markup.html.form.ChoiceRenderer;


public class StateChoiceRenderer extends ChoiceRenderer<State> {
    private static final long serialVersionUID = 1L;
    @Override
    public Object getDisplayValue(State object) {
        if (object == null) {
            return "";
        }
        return object.getName() + " (" + object.getAbbreviation() + ')';
    }
    @Override
    public String getIdValue(State object, int index) {
        return object.getAbbreviation();
    }
}
