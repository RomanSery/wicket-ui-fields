package org.coderdreams.wicketfields.fields.dropdown;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.markup.html.form.select.Select;
import org.apache.wicket.extensions.markup.html.form.select.SelectOption;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.IAjaxUiField;

public class AjaxGroupedDropdownField<K extends Serializable, T extends Serializable> extends BaseUiField<T> implements IAjaxUiField {
	private static final long serialVersionUID = 1L;	

	private Select<T> fieldInput;
	private IModel<Map<K, List<T>>> choiceList;

    public AjaxGroupedDropdownField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.choiceList = args.getChoiceList();
    }
	
	protected String convertToString(T obj) {
        return obj != null ? obj.toString() : null;
    }
	protected String convertKeyToString(K obj) {
        return obj.toString();
    }

	@Override
	protected void initField() {
		
		fieldInput = new Select<T>("fieldInput", model);
		List<K> headers = choiceList != null ? new ArrayList<K>(choiceList.getObject().keySet()) : Collections.emptyList();
		
		RepeatingView optGrps = new RepeatingView("optGrps");
		for(K h : headers) {

			WebMarkupContainer optgrp = new WebMarkupContainer(optGrps.newChildId());
			optgrp.add(new AttributeModifier("label", convertKeyToString(h)));
			optGrps.addOrReplace(optgrp);
			
			List<T> sectionOpts = choiceList != null ? choiceList.getObject().get(h) : Collections.emptyList();
			
			RepeatingView opts = new RepeatingView("opts");
			for(T o : sectionOpts) {
				SelectOption<T> s = new SelectOption<T>(opts.newChildId(), Model.of(o));
				s.addOrReplace(new Label("txt", convertToString(o)).setRenderBodyOnly(true));
				opts.addOrReplace(s);
			}
			opts.setRenderBodyOnly(true);
			optgrp.addOrReplace(opts);
		}		 
		 
		optGrps.setRenderBodyOnly(true);
		fieldInput.addOrReplace(optGrps);

		fieldInput.setOutputMarkupId(true);
        addOrReplace(fieldInput);
		
		fieldInput.add(new OnChangeAjaxBehavior() {
			private static final long serialVersionUID = 1L;			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				onFieldChanged(target);
			}
		});
	}

    @Override protected String getDisabledLbl() { return (model.getObject() == null ? "" : convertToString(model.getObject())); }
    @Override
    protected String getInnerHtml() {
        return "<div class=\"col-xs-10\" style=\"padding:0\">" +
                " <select wicket:id=\"fieldInput\">" +
                "  <option value=\"\">Select one</option>" +
                " <optgroup wicket:id=\"optGrps\"> <option wicket:id=\"opts\"><span wicket:id=\"txt\"></span></option> </optgroup>" +
                " </select>" +
                " </div>";
    }

	@Override public Select<T> getField() {
		return fieldInput;
	}
}
