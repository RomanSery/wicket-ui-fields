package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.markup.html.form.TextField;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class TxtField<T> extends BaseUiField<T> {

    private static final long serialVersionUID = 1L;

    private TextField<T> fieldInput;

    public TxtField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void initField() {
        fieldInput = new TextField<T>("fieldInput", model, getFormFieldType(null));
        fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
    }

    @Override
    protected String getInnerHtml() {
        return "<input type=\"text\" wicket:id=\"fieldInput\" class=\"wf-txt-field\" />";
    }

    @Override
    public TextField<T> getField() {
        return fieldInput;
    }



}
