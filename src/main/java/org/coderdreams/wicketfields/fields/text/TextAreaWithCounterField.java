package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextArea;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class TextAreaWithCounterField extends BaseUiField<String> {

	private static final long serialVersionUID = 1L;	

	private TextArea<String> fieldInput;
	private int rows;
	private int maxLength;
	private String countMsgId;

    public TextAreaWithCounterField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.rows = args.getRows() > 0 ? args.getRows() : 3;
        this.maxLength = args.getMaxLength();
    }

	@Override
	protected void initField() {
        Label countMsg = new Label("countMsg", "");
        addOrReplace(countMsg.setOutputMarkupId(true));
        countMsgId = countMsg.getMarkupId();

	    fieldInput = new TextArea<String>("fieldInput", model) {
			private static final long serialVersionUID = 1L;
            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("rows", rows);
                tag.put("maxlength", maxLength);
                tag.put("count-msg-id", countMsgId);
            }
		};
		fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
	}

    @Override public Class<String> getDefiniteType() { return String.class; }
    @Override
    protected String getInnerHtml() {
        return  "<textarea wicket:id=\"fieldInput\" class=\"col-xs-10 txt-char-counter\"></textarea>" +
                "<div class=\"clearfix\"></div>" +
                "<h6 wicket:id=\"countMsg\"></h6>";
    }

	@Override
	public TextArea<String> getField() {
		return fieldInput;
	}

}
