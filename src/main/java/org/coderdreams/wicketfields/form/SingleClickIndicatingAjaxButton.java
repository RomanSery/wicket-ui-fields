package org.coderdreams.wicketfields.form;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SingleClickIndicatingAjaxButton extends IndicatingAjaxButton {
	private static final long serialVersionUID = 1L;
    public static final Logger log = LoggerFactory.getLogger( SingleClickIndicatingAjaxButton.class ) ;

    private final boolean enableButtonAfterSubmit;

	public SingleClickIndicatingAjaxButton(String id, Form<?> form, boolean enableButtonAfterSubmit) {
		super(id, form);
		this.enableButtonAfterSubmit = enableButtonAfterSubmit;
	}

    @Override
    protected void onError(AjaxRequestTarget target) {
        error(target);
        target.appendJavaScript("$('#"+this.getMarkupId()+"').prop('disabled', false);");
    }

    @Override
    protected void onSubmit(AjaxRequestTarget target) {
        if(!submit(target)) {
            target.appendJavaScript("$('#"+this.getMarkupId()+"').prop('disabled', false);");
        }
    }

    @Override
    protected void onAfterSubmit(AjaxRequestTarget target) {
        if(enableButtonAfterSubmit) {
            target.appendJavaScript("$('#"+this.getMarkupId()+"').prop('disabled', false);");
        }
    }

    /**
     * @return true to not re-enable the button, false to re-enable the button
     */
    protected abstract boolean submit(AjaxRequestTarget target);

    @Override protected String getOnClickScript() {
        return "$('#"+this.getMarkupId()+"').prop('disabled', true); setTimeout(function() { $('#"+this.getMarkupId()+"').prop('disabled', false); }, 10000);";
    }

    protected void error(AjaxRequestTarget target) { }

}
