package org.coderdreams.wicketfields.fields.bool;


import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.model.YesNoUnknownChoice;

public class YesNoUnknownField extends BaseUiField<YesNoUnknownChoice> {

	private static final long serialVersionUID = 1L;

	private RadioGroup<YesNoUnknownChoice> myRadioGroup;

    public YesNoUnknownField(FieldArgs args) {
        super(args);
    }

	@Override
	protected void initField() {
		
		myRadioGroup = new RadioGroup<YesNoUnknownChoice>("myRadioGroup", model);
		myRadioGroup.setLabel(Model.of(fieldLabel));
        addOrReplace(myRadioGroup);

		myRadioGroup.addOrReplace(new Radio<YesNoUnknownChoice>("myRadioChoiceYes", new Model<YesNoUnknownChoice>(YesNoUnknownChoice.YES)));
		myRadioGroup.addOrReplace(new Radio<YesNoUnknownChoice>("myRadioChoiceNo", new Model<YesNoUnknownChoice>(YesNoUnknownChoice.NO)));
		myRadioGroup.addOrReplace(new Radio<YesNoUnknownChoice>("myRadioChoiceUnknown", new Model<YesNoUnknownChoice>(YesNoUnknownChoice.UNKNOWN)));
	}

    @Override protected String getDisabledLbl() { return model == null || model.getObject() == null ? "" : model.getObject().getDescription(); }
    @Override public Class<YesNoUnknownChoice> getDefiniteType() { return YesNoUnknownChoice.class; }
    @Override
    protected String getInnerHtml() {
        return "<div wicket:id=\"myRadioGroup\">" +
                "<input type=\"radio\" wicket:id=\"myRadioChoiceYes\" /> <span class=\"lbl\">Yes</span>&nbsp; " +
                "<input type=\"radio\" wicket:id=\"myRadioChoiceNo\" /> <span class=\"lbl\">No</span>&nbsp; " +
				"<input type=\"radio\" wicket:id=\"myRadioChoiceUnknown\" /> <span class=\"lbl\">Unknown</span> " +
                "</div> ";
    }

	@Override
	public RadioGroup<YesNoUnknownChoice> getField() {
		return myRadioGroup;
	}
}
