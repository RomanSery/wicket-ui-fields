package org.coderdreams.wicketfields.fields.dates;

import org.apache.wicket.markup.html.form.FormComponent;
import org.coderdreams.wicketfields.DateService;
import org.coderdreams.wicketfields.model.LocalDateRangeModel;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.internal.LocalDateRangePickerControl;

public class LocalDateRangeField extends BaseUiField<LocalDateRangeModel> {
	
	private static final long serialVersionUID = 1L;	

	private DateService dateService;

    public LocalDateRangeField(FieldArgs args) {
        super(args);
    }
	
	@Override
	protected void initField() {

        LocalDateRangePickerControl fieldInput = new LocalDateRangePickerControl("fieldInput", model.getObject(), false, fieldLabel);
        addOrReplace(fieldInput.setOutputMarkupId(true));
	}

    @Override
    protected String getDisabledLbl() {
        return model != null && model.getObject() != null ? dateService.format(model.getObject().getStartDateModel().getObject()) + " - " + dateService.format(model.getObject().getEndDateModel().getObject())  : "";
	}
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" wicket:id=\"fieldInput\"></div> ";
    }


    @Override
	public FormComponent<LocalDateRangeModel> getField() {
		return null;
	}
}
