package org.coderdreams.wicketfields.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.wicket.Component;
import org.apache.wicket.resource.Properties;
import org.apache.wicket.resource.UtfPropertiesFilePropertiesLoader;
import org.apache.wicket.resource.loader.IStringResourceLoader;
import org.apache.wicket.util.value.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class FieldPropsResourceLoader implements IStringResourceLoader {
	private static final Logger log = LoggerFactory.getLogger(FieldPropsResourceLoader.class);
	
	private Properties fileProps = null;

	FieldPropsResourceLoader() {
		super();
		initProperties();
	}

	private String getIdentifier() {
		return "field_props";
	}

	private void initProperties() {
		if(fileProps != null) {
			return;
		}

		java.util.Properties merged = new java.util.Properties();

		java.util.Properties defaultProps = getProperties(getIdentifier() + ".properties");
		if(defaultProps != null) {
			merged.putAll(defaultProps);
		}
		String evn = getEnv();
		if(evn != null) {
			java.util.Properties envProps = getProperties(getIdentifier()+"_"+evn+".properties");
			if(envProps != null) {
				merged.putAll(envProps);
			}
		}

		ValueMap data = new ValueMap();
		Enumeration<?> enumeration = merged.propertyNames();
		while (enumeration.hasMoreElements()) {
			String property = (String)enumeration.nextElement();
			data.put(property, merged.getProperty(property));
		}

		fileProps = new Properties(getIdentifier(), data);
	}
	
	
	private java.util.Properties getProperties(String path) {

        try (InputStream in = FieldPropsResourceLoader.class.getClassLoader().getResourceAsStream(path)) {
            UtfPropertiesFilePropertiesLoader loader = new UtfPropertiesFilePropertiesLoader(null, StandardCharsets.UTF_8.name());
            if(in == null) {
                return null;
            }
            return loader.loadJavaProperties(in);
        } catch (IOException e) {
            log.error("failed getProperties({})", path, e);
        }

		return null;	
	}
	
	
	private String load(String key) {		
		if(fileProps == null) return null;
		
		String value = fileProps.getString(key);
		if (value != null) {
			return value;
		}
		
		return null;
	}

	@Override
	public String loadStringResource(Class<?> clazz, String key, Locale locale, String style, String variation) {
		return load(key);
	}

	@Override
	public String loadStringResource(Component component, String key, Locale locale, String style, String variation) {
		return load(key);
	}

	private static String getEnv() {
		String env = System.getProperty("env");
		if (StringUtils.isBlank(env)) {
			env = System.getenv("env");
		}
		return env;
	}
}
