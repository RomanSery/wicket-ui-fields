package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.TextArea;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class TextAreaField extends BaseUiField<String> {
	private static final long serialVersionUID = 1L;	

	private TextArea<String> fieldInput;
	private int rows;

    public TextAreaField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.rows = args.getRows() > 0 ? args.getRows() : 3;
    }


	@Override
	protected void initField() {
		fieldInput = new TextArea<String>("fieldInput", model) {		
			private static final long serialVersionUID = 1L;
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("rows", rows);
            }
		};					

		fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
	}

    @Override protected Class getDefiniteType() { return String.class; }
    @Override protected String getInnerHtml() { return "<textarea wicket:id=\"fieldInput\" class=\"col-xs-10\"></textarea>"; }

	@Override
	public TextArea<String> getField() {
		return fieldInput;
	}
	

}
