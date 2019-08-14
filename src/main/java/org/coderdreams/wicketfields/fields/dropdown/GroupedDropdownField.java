package org.coderdreams.wicketfields.fields.dropdown;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class GroupedDropdownField extends BaseUiField<String> {
	private static final long serialVersionUID = 1L;	

	private Select<String> fieldInput;
	private IModel<Map<String, List<String>>> choiceList;

    public GroupedDropdownField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.choiceList = args.getChoiceList();
    }

	@Override
	protected void initField() {
		
		fieldInput = new Select<String>("fieldInput", model);
		
		List<String> headers = choiceList != null ? new ArrayList<String>(choiceList.getObject().keySet()) : Collections.emptyList();
				
		
		RepeatingView optGrps = new RepeatingView("optGrps");
		for(String h : headers) {

			WebMarkupContainer optgrp = new WebMarkupContainer(optGrps.newChildId());
			optgrp.add(new AttributeModifier("label", h));
			optGrps.addOrReplace(optgrp);
			
			List<String> sectionOpts = choiceList.getObject().get(h);						
			
			RepeatingView opts = new RepeatingView("opts");
			for(String o : sectionOpts) {
				SelectOption<String> s = new SelectOption<String>(opts.newChildId(), Model.of(o));
				s.addOrReplace(new Label("txt", o).setRenderBodyOnly(true));
				opts.addOrReplace(s);
			}
			opts.setRenderBodyOnly(true);
			optgrp.addOrReplace(opts);
		}		 
		 
		optGrps.setRenderBodyOnly(true);
		fieldInput.addOrReplace(optGrps);
		 
		fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
	}

    @Override
    protected String getInnerHtml() {
        return "<select class=\"col-xs-10 wf-grouped-dropdown-field\" wicket:id=\"fieldInput\"> <optgroup wicket:id=\"optGrps\"> <option wicket:id=\"opts\"><span wicket:id=\"txt\"></span></option> </optgroup> </select>";
    }

	@Override
	public Select<String> getField() {
		return fieldInput;
	}
}
