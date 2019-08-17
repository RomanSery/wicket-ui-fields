package org.coderdreams.wicketfields.form;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;

public abstract class SingleClickIndicatingAjaxButton extends IndicatingAjaxButton {
	private static final long serialVersionUID = 1L;
    private final IModel<String> confirmationMessageModel;
    private final boolean enableButtonAfterSubmit;

    public SingleClickIndicatingAjaxButton(String id, Form<?> form, boolean enableButtonAfterSubmit, IModel<String> confirmationMessageModel) {
        super(id, form);
        this.enableButtonAfterSubmit = enableButtonAfterSubmit;
        this.confirmationMessageModel = confirmationMessageModel;
    }

    @Override
    protected void onError(AjaxRequestTarget target) {
        //on error, just re-enable
        error(target);
        enableButton(target);
    }

    @Override
    protected void onAfterSubmit(AjaxRequestTarget target) {
        if(enableButtonAfterSubmit) {
            enableButton(target);
        }
    }

    protected void enableButton(AjaxRequestTarget target) {
        target.appendJavaScript("$('#"+this.getMarkupId()+"').prop('disabled', false);");
    }

    @Override
    protected String getOnClickScript() {
        //if the button should be re-enabled but hasnt due to some error, automatically re-enable it after 10 seconds
        if(enableButtonAfterSubmit) {
            return "$('#"+this.getMarkupId()+"').prop('disabled', true); setTimeout(function() { $('#"+this.getMarkupId()+"').prop('disabled', false); }, 10000);";
        }
        return null;
    }

    protected void error(AjaxRequestTarget target) { }

    @Override
    protected void updateAjaxAttributes(AjaxRequestAttributes attributes) {
        //show a JS confirm prompt
        if(confirmationMessageModel != null && confirmationMessageModel.getObject() != null) {
            attributes.getAjaxCallListeners().add(new AjaxCallListener(){
                private static final long serialVersionUID = 1L;
                @Override
                public CharSequence getPrecondition(Component component) {
                    if(enableButtonAfterSubmit) {
                        String str = "$('#"+ SingleClickIndicatingAjaxButton.this.getMarkupId()+"').prop('disabled', false);";
                        return "if (!confirm('" + confirmationMessageModel.getObject() + "')) { "+str+" return false; }";
                    } else {
                        return "if (!confirm('" + confirmationMessageModel.getObject() + "')) { return false; }";
                    }

                }
            });
        }
    }
}
