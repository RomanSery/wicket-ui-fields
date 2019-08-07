package org.coderdreams.wicketfields.fields.text;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import org.coderdreams.wicketfields.BaseUiField;
import org.coderdreams.wicketfields.FieldArgs;

public class LinkField<T extends Page> extends BaseUiField<Integer> {

	private static final long serialVersionUID = 1L;

    private Class pageClass;
	private IModel<String> linkTxt;

    public LinkField(FieldArgs args) {
        super(args);
    }

    @Override
    protected void assignFromArgs(FieldArgs args) {
        this.pageClass = args.getPageClass();
        this.linkTxt = args.getTxtModel();
    }

	@Override
	protected void initField() {

        BookmarkablePageLink<T> fieldInput = new BookmarkablePageLink<T>("fieldInput", pageClass, new PageParameters().add("id", model != null && model.getObject() != null ? model.getObject() : 0));
		fieldInput.setBody(linkTxt);
		fieldInput.setOutputMarkupId(true);
		fieldInput.setOutputMarkupPlaceholderTag(true);
		fieldInput.setEscapeModelStrings(false);
        addOrReplace(fieldInput);
	}

    @Override
    protected void initDisabledField() {
        this.initField();
    }

	@Override protected boolean hasDisabledLbl() { return false; }
    @Override
    protected String getInnerHtml() {
        return "<a wicket:id=\"fieldInput\" target=\"_blank\" class=\"col-xs-10\"></a>";
    }

	@Override
	public FormComponent<Integer> getField() {
		return null;
	}

	@Override
	public boolean isEnabledInHierarchy() {
		return true;
	}
}
