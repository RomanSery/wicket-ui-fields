package org.coderdreams.wicketfields.fields.numeric;

import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class NumberSpinnerField<T extends Number & Comparable<T>> extends BaseUiField<T> {
	private static final long serialVersionUID = 1L;	
		
	private NumberTextField<T> fieldInput;
	private T min;
	private T max;
	private T step;

    public NumberSpinnerField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.min = (T) args.getMin();
        this.max = (T) args.getMax();
		this.step = (T) args.getStep();
    }

	@Override
	protected void initField() {
		fieldInput = new NumberTextField<T>("fieldInput", model, getFormFieldType(null));

		fieldInput.setOutputMarkupId(true);
		fieldInput.setMinimum(min);
		fieldInput.setMaximum(max);
		fieldInput.setStep(step);
        addOrReplace(fieldInput);
	}

    @Override
    protected String getInnerHtml() {
        return "<input type=\"number\" wicket:id=\"fieldInput\" class=\"col-xs-2 text-center wf-number-spinner-field\" />";
    }

	@Override
	public TextField<T> getField() {
		return fieldInput;
	}


}
