package org.coderdreams.wicketfields.fields.dropdown;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class MultiDropdownField<T> extends BaseUiField<List<T>> {

	private static final long serialVersionUID = 1L;	

	private ListMultipleChoice<T> fieldInput;
	protected IModel<? extends List<? extends T>> choiceList;
	private IModel<List<T>> listModel;

    public MultiDropdownField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.choiceList = args.getChoiceList();
        this.listModel = args.getModel();
    }

	@Override
	protected void initField() {
		
		if(cr != null) {
            fieldInput = new ListMultipleChoice<T>("fieldInput", listModel, choiceList, cr);
        } else {
            fieldInput = new ListMultipleChoice<T>("fieldInput", listModel, choiceList);
        }

		fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
	}

    @Override
    protected String getDisabledLbl() {
        List<String> arr = new ArrayList<String>();
        if(listModel != null) {
            List<T> lst = listModel.getObject();
            if(lst != null) {
                for(T obj : lst) {
                    arr.add(cr != null ? (String)cr.getDisplayValue(obj) : (obj == null ? "" : obj.toString()));
                }
            }
        }
        return StringUtils.join(arr, "<br />");
    }

    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10 wf-multi-dropdown-field\" style=\"padding:0\"> <select class=\"ie_expand wicket-ui-select2\" multiple=\"multiple\" wicket:id=\"fieldInput\"></select></div>";
    }


	@Override
	public FormComponent<List<T>> getField() {
		return (FormComponent) fieldInput;
	}

}
