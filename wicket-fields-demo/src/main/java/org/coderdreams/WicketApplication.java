package org.coderdreams;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.apache.wicket.request.resource.caching.FilenameWithVersionResourceCachingStrategy;
import org.apache.wicket.request.resource.caching.version.MessageDigestResourceVersion;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.settings.RequestCycleSettings;
import org.apache.wicket.util.time.Duration;

public class WicketApplication extends WebApplication {

	public WicketApplication() {
	}

	@Override
	public void init() {
		super.init();

		getPageSettings().setVersionPagesByDefault(false);
		getStoreSettings().setInmemoryCacheSize(0);
		getRequestCycleSettings().setRenderStrategy(RequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
		getRequestCycleSettings().setTimeout(Duration.minutes(5));
		this.getExceptionSettings().setAjaxErrorHandlingStrategy(ExceptionSettings.AjaxErrorStrategy.REDIRECT_TO_ERROR_PAGE);

		getResourceSettings().setCachingStrategy(new FilenameWithVersionResourceCachingStrategy(new MessageDigestResourceVersion()));
		getResourceSettings().setThrowExceptionOnMissingResource(false);

		getDebugSettings().setComponentUseCheck(false);
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getMarkupSettings().setStripWicketTags(true);
	}

	public Class getHomePage()
	{
		return UiFieldsDemo.class;
	}

}
