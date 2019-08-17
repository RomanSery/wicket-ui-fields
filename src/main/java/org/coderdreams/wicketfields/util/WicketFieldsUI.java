package org.coderdreams.wicketfields.util;


import org.apache.wicket.protocol.http.WebApplication;

public class WicketFieldsUI {

    public static void init(WebApplication webApp) {
        if(webApp == null) {
            return;
        }

        FieldPropsResourceLoader propsLoader = new FieldPropsResourceLoader();
        webApp.getResourceSettings().getStringResourceLoaders().add(propsLoader);
    }
}
