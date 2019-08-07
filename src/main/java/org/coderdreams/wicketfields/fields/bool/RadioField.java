package org.coderdreams.wicketfields.fields.bool;


import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class RadioField extends BaseUiField<Boolean> {
	
	private static final long serialVersionUID = 1L;	
		
	private RadioGroup<Boolean> myRadioGroup;
	private String yesText, noText;

    public RadioField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.yesText = args.getYesText() != null ? args.getYesText() : "Yes";
        this.noText = args.getNoText() != null ? args.getNoText() : "No";
    }
	
	@Override
	protected void initField() {
		
		myRadioGroup = new RadioGroup<Boolean>("myRadioGroup", model);
		myRadioGroup.setLabel(Model.of(fieldLabel));
        addOrReplace(myRadioGroup);
		
		Radio<Boolean> myRadioChoiceYes = new Radio<Boolean>("myRadioChoiceYes", new Model<Boolean>(Boolean.TRUE));
		myRadioGroup.addOrReplace(myRadioChoiceYes);
		myRadioGroup.addOrReplace(new Label("myYesText", yesText));
		
		Radio<Boolean> myRadioChoiceNo = new Radio<Boolean>("myRadioChoiceNo", new Model<Boolean>(Boolean.FALSE));
		myRadioGroup.addOrReplace(myRadioChoiceNo);
		myRadioGroup.addOrReplace(new Label("myNoText", noText));
	}

    @Override protected String getDisabledLbl() { return model == null || model.getObject() == null ? "" : (model.getObject() == true ? yesText : noText); }
    @Override public Class<Boolean> getDefiniteType() { return Boolean.class; }
    @Override
    protected String getInnerHtml() {
        return "<div wicket:id=\"myRadioGroup\">" +
                "<input type=\"radio\" wicket:id=\"myRadioChoiceYes\" /> " +
                "<span class=\"lbl\" wicket:id=\"myYesText\">Yes</span> " +
                "<input type=\"radio\" wicket:id=\"myRadioChoiceNo\" /> " +
                "<span class=\"lbl\" wicket:id=\"myNoText\">No</span> " +
                "</div> ";
    }

	@Override
	public RadioGroup<Boolean> getField() {
		return myRadioGroup;
	}

	
}
