package org.coderdreams;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.coderdreams.wicketfields.FieldArgs;
import org.coderdreams.wicketfields.event.InitPanelFieldsEvent;
import org.coderdreams.wicketfields.fields.text.TxtField;
import org.coderdreams.wicketfields.form.SingleClickIndicatingAjaxButton;

public class UiFieldsDemo extends WebPage {
	private static final long serialVersionUID = 1L;

	private Form<Void> fieldsForm;

	private DemoFormObj formData;

    public UiFieldsDemo(final PageParameters parameters) {
        super();
        formData = new DemoFormObj();

        fieldsForm = new Form<Void>("fieldsForm");
        addOrReplace(fieldsForm);

        addFields();

        fieldsForm.add(new SingleClickIndicatingAjaxButton("saveButton", fieldsForm, true) {
            private static final long serialVersionUID = 1L;
            @Override protected boolean submit(AjaxRequestTarget target) {
                System.out.println(formData.getTxtField());
                return true;
            }
        });

        send(this, Broadcast.BREADTH, new InitPanelFieldsEvent(null));
    }

    private void addFields() {
        fieldsForm.addOrReplace(new TxtField<String>(FieldArgs.Builder.of("TxtField", "Text Field", LambdaModel.of(formData::getTxtField, formData::setTxtField))
                .l(2).build()));
    }
}
