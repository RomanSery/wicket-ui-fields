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
        response.render(css("select2.min.css"));
        response.render(css("bootstrap-datetimepicker.css"));
        response.render(css("datepicker.css"));
        response.render(css("select2-custom.css"));
        response.render(css("custom_styles.css"));

        response.render(js("moment.min.js"));
        response.render(js("select2.min.js"));
        response.render(js("bootstrap-datepicker.js"));
        response.render(js("jquery.alphanum.js"));
        response.render(js("jquery.maskedinput.min.js"));
        response.render(js("jquery.maskMoney.min.js"));
        response.render(js("bootstrap-datetimepicker.min.js"));
        response.render(js("jquery.inputmask.bundle.min.js"));



//		<script src="javascript/date-time/bootstrap-datepicker.min.js"></script>
//		<script src="javascript/jquery.maskedinput.min.js"></script>
//        <script src="javascript/jquery.inputmask.bundle.min.js"></script>
//		<script src="javascript/date-time/moment.min.js"></script>
//		<script src="javascript/date-time/bootstrap-datetimepicker.js"></script>
//		<script src="javascript/jquery.alphanum.js"></script>
//		<script src="javascript/jquery.nicescroll.min.js"></script>
//		<script src="javascript/autocomplete/jquery.auto-complete.min.js"></script>
//		<script src="javascript/bootstrap-hover-dropdown.min.js"></script>
//		<script src="javascript/jquery.maskMoney.min.js"></script>
//		<script src="libs/clipboard.min.js"></script>
//
//		<!-- ace settings handler -->
//		<script src="dist/js/ace-extra.min.js?v=3"></script>
//		<script src="javascript/atc.min.js"></script>
//		<script src="javascript/bluebird.min.js"></script>
//		<script src="javascript/velocity.min.js"></script>


        response.render(js("common_scripts.js"));

        response.render(OnDomReadyHeaderItem.forScript("initClientSideScripts()"));
    }

    private static JavaScriptHeaderItem js(String resource) {
        return JavaScriptHeaderItem.forReference(new PackageResourceReference(UiFieldsBehavior.class, resource));
    }

    private static CssReferenceHeaderItem css(String resource) {
        return CssHeaderItem.forReference(new PackageResourceReference(UiFieldsBehavior.class, resource));
    }

}
