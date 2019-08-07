package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.FormComponent;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class LabelField<T> extends BaseUiField<T> {
	private static final long serialVersionUID = 1L;

    public LabelField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
        Label fieldInput = new Label("fieldInput", model);
		fieldInput.setOutputMarkupId(true);
		fieldInput.setOutputMarkupPlaceholderTag(true);
		fieldInput.setEscapeModelStrings(false);
        addOrReplace(fieldInput);
	}

    @Override
    protected void initDisabledField() {
        this.initField();
    }

	@Override protected boolean hasDisabledLbl() { return false; }
    @Override
    protected String getInnerHtml() {
        return "<div wicket:id=\"fieldInput\" class=\"col-xs-10\" style=\"font-weight:400; font-size:14px;\"></div>";
    }

	@Override
	public FormComponent<T> getField() {
		return null;
	}
}
