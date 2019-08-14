package org.coderdreams.wicketfields.resources;


import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.request.resource.PackageResourceReference;

public class UiFieldsBehavior extends Behavior {
    private static final long serialVersionUID = 1L;

    @Override
    public void renderHead(final Component component, final IHeaderResponse response) {
        renderCss(component, response);
        renderJsScripts(component, response);
        renderOnDomReadyScript(component, response);
    }

    protected void renderCss(final Component component, final IHeaderResponse response) {
        response.render(css("select2.min.css"));
        response.render(css("bootstrap-datetimepicker.css"));
        response.render(css("datepicker.css"));
        response.render(css("select2-custom.css"));
        response.render(css("custom_styles.css"));

    }

    protected void renderJsScripts(final Component component, final IHeaderResponse response) {
        response.render(js("moment.min.js"));
        response.render(js("select2.min.js"));
        response.render(js("bootstrap-datepicker.js"));
        response.render(js("jquery.alphanum.js"));
        response.render(js("jquery.maskedinput.min.js"));
        response.render(js("jquery.maskMoney.min.js"));
        response.render(js("bootstrap-datetimepicker.min.js"));
        response.render(js("jquery.inputmask.bundle.min.js"));
        response.render(js("common_scripts.js"));
    }

    protected void renderOnDomReadyScript(final Component component, final IHeaderResponse response) {
        response.render(OnDomReadyHeaderItem.forScript("wicketUiFieldScripts.initClientSideScripts()"));
    }

    protected static JavaScriptHeaderItem js(String resource) {
        return JavaScriptHeaderItem.forReference(new PackageResourceReference(UiFieldsBehavior.class, resource));
    }

    protected static CssReferenceHeaderItem css(String resource) {
        return CssHeaderItem.forReference(new PackageResourceReference(UiFieldsBehavior.class, resource));
    }

}
