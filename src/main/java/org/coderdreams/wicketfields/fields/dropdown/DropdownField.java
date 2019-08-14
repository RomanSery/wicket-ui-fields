package org.coderdreams.wicketfields.fields.dropdown;

import java.util.List;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class DropdownField<T> extends BaseUiField<T> {

	private static final long serialVersionUID = 1L;	

	private DropDownChoice<T> fieldInput;
    private IModel<? extends List<? extends T>> choiceList;
    private boolean dontDisplayClearOpt;

    public DropdownField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.choiceList = args.getChoiceList();
        this.dontDisplayClearOpt = args.isDontDisplayClearOpt();
    }

	protected String getDropdownClass() {
		return dontDisplayClearOpt ? " wicket-ui-select2-noclear" : " wicket-ui-select2";
	}

	@Override
	protected void initField() {
		
		if(cr != null) {
			fieldInput = new DropDownChoice<T>("fieldInput", model, choiceList, cr) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onComponentTag(final ComponentTag tag) {
					super.onComponentTag(tag);
					tag.put("class", getDropdownClass());
				}
                @Override protected String getNullValidDisplayValue() { return nullChoiceTxt; }
			};
		} else {
			fieldInput = new DropDownChoice<T>("fieldInput", model, choiceList) {		
				private static final long serialVersionUID = 1L;
				@Override
				protected void onComponentTag(final ComponentTag tag) {
					super.onComponentTag(tag);
					tag.put("class", getDropdownClass());
				}
                @Override protected String getNullValidDisplayValue() { return nullChoiceTxt; }
			};	
		}

		fieldInput.setOutputMarkupId(true);
		fieldInput.setNullValid(dontDisplayClearOpt ? false : true);
        addOrReplace(fieldInput);
	}

    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10 wf-dropdown-field\" style=\"padding:0\"><select wicket:id=\"fieldInput\"></select></div>";
    }

	@Override
	public DropDownChoice<T> getField() {
		return fieldInput;
	}

}
