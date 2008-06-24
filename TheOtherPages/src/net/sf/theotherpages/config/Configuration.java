package net.sf.theotherpages.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import net.sf.theotherpages.PaginationConstants;

import org.apache.commons.lang.StringUtils;

/**
 * Contains the system configuration related information for RxConnect
 * application.
 * <p>
 * <b>Overview: </b>
 * <p>
 * System related properties are kept in <code>theotherpages.properties</code>.
 * <code>Configuration</code> is implemented as a singleton. The instance gets
 * created in the first call and in turn properties are loaded and cached.
 * <p>
 * <p>
 * For using <code>Configuration</code>, one can get the instance of it using
 * static <code>getInstance</code> method. Using <code>get</code> method one
 * can get the value of the property.
 * <p>
 * <b>Usage Example: </b>
 * 
 * <pre>
 * Configuration cfg = Configuration.getInstance();
 * 
 * String propertyValue = cfg.get(&quot;samplekey&quot;);
 * </pre>
 * 
 * 
 * <p>
 * <DL>
 * <DT><B>History: </B>
 * <DD>Jan 22, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @since v1.0
 * 
 */
public class Configuration {

	private static final String CACHE_INDICATOR = "cache.indicator";

	private static final String NUM_PAGES = "numPages";

	private static final String PAGE_SIZE = "page.size";

	public static final String CURRENT_PAGE_NUMBER = "currentPageNumber";

	public static final String SORT_STR = "sortStr";

	public static final String TRUE = "true";

	public static final String PAGESIZE = "pagesize";

	public static final String DEFAULT_PREFIX = "default";

	private static final String DOT = ".";

	/**
	 * map to cache data from properties file
	 */
	private static Map pageConfigMap = new HashMap();

	/** ********************************************************** */

	// Context
	/**
	 * JNDI is a constant
	 */
	public static final String JNDI = "JNDI";

	/**
	 * RxConnect Apllication Level property
	 */
	private static final String RXCONNECT_PROPERTIES = "/theotherpages.properties";

	/**
	 * EXTERNAL is a constant variable
	 */
	public static final String EXTERNAL = "EXTERNAL";

	private Properties props = null;

	/**
	 * property_context
	 */
	public static String property_context = "";

	private static Configuration config = null;

	private Configuration() {
		InputStream stream = this.getClass().getResourceAsStream(
				property_context + RXCONNECT_PROPERTIES);
		props = new Properties();
		if (stream == null) {
			throw new IllegalStateException(
					"Exception occured while loading property file "
							+ property_context
							+ RXCONNECT_PROPERTIES
							+ ". Please place the rxconnect.properties in classpath");
		}
		try {
			props.load(stream);

			// Override with any -D parameters passed in at the command line
			Enumeration systemKeys = System.getProperties().keys();
			Enumeration systemElements = System.getProperties().elements();

			String dkey = null;
			String dvalue = null;

			while (systemKeys.hasMoreElements()) {
				dkey = (String) systemKeys.nextElement();
				dvalue = (String) systemElements.nextElement();

				if (props.containsKey(dkey)) {
					props.put(dkey, dvalue);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			IllegalStateException exp = new IllegalStateException(
					"Exception occured while loading property file "
							+ RXCONNECT_PROPERTIES);
			exp.initCause(e);
			throw exp;
		} catch (RuntimeException e) { // parasoft-suppress CODSTA.NCE "Logic
			// requires that exception to be caught"
			e.printStackTrace();
			throw e; // parasoft-suppress EXCEPT.NTERR "Logic requires that
			// exception be thrown"
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				IllegalStateException ex = new IllegalStateException(
						"An I/O error occured while closing the stream");
				ex.initCause(e);
				throw ex;
			}

		}
	}

	/**
	 * Gets the instance of <code>Configuration</code>.
	 * 
	 * @return instance of Configuration
	 */
	public static Configuration getInstance() {
		if (null == config) {
			config = new Configuration();
		}
		return config;
	}

	/**
	 * gets properties
	 * 
	 * @return <code>Properties</code> props
	 * 
	 */
	public Properties getProperties() {
		return props;
	}

	/**
	 * To get property from the properties loaded from .properties file.
	 * 
	 * @param propName
	 * @return <code>String</code> property
	 * 
	 */
	public String getProperty(String propName) {
		return props.getProperty(propName);
	}

	/**
	 * To get specfic int property from the properties loaded from .properties
	 * file.
	 * 
	 * @param propName
	 *            Name of the property
	 * @return <code>int</code> integer value as defined in properties
	 */
	public int getIntProperty(String propName) {
		int intProperty = 0;
		String propValue = props.getProperty(propName);
		if (StringUtils.isNotBlank(propValue)) {
			intProperty = Integer.valueOf(propValue).intValue();
		}
		return intProperty;
	}

	/**
	 * To get specfic boolean property from the properties loaded from
	 * .properties file.
	 * 
	 * @param propName
	 *            Name of the property
	 * @return <code>boolean</code> true / based based on "true" / "false"
	 *         value specified in properties file
	 */
	public boolean getBooleanProperty(String propName) {
		boolean booleanProp = false;
		String propValue = props.getProperty(propName);
		if (StringUtils.isNotBlank(propValue)) {
			booleanProp = Boolean.valueOf(propValue).booleanValue();
		}
		return booleanProp;
	}

	/**
	 * Returns all the properties for a given context
	 * 
	 * @param contextRoot
	 * @return <code>Properties</code>
	 * @throws IllegalStateException
	 * 
	 */
	public Properties getAllPropertiesForContext(String contextRoot)
			throws IllegalStateException {
		Properties allForContext = null;

		Enumeration enumerator = props.propertyNames();
		String key = null;
		String value = null;

		while (enumerator.hasMoreElements()) {
			key = (String) enumerator.nextElement();
			value = (String) props.get(key);

			if (key.startsWith(contextRoot + '.')) {
				// Strip off the context from the key
				key = key.substring(contextRoot.length() + 1, key.length());

				if (allForContext == null) {
					allForContext = new Properties();
				}

				allForContext.put(key, value);
			}
		}

		return allForContext;
	}

	/**
	 * Return property in a context
	 * 
	 * @param contextRoot
	 * @param key
	 * @return <code>String</code>
	 * @throws IllegalStateException
	 * 
	 */
	public String getProperty(String contextRoot, String key)
			throws IllegalStateException {
		String property = null;
		String propertyKey = contextRoot + '.' + key;
		property = (String) props.get(propertyKey);

		return property;
	}

	/**
	 * Returns the ResourceBundle for application
	 * 
	 * @return <code>ResourceBundle</code>
	 * 
	 * 
	 */
	public ResourceBundle getResourceBundle() {
		return ResourceBundle.getBundle("messages");
	}

	/**
	 * Reloads the rxonnect.properties
	 */
	public static void reload() {
		config = new Configuration();
	}

	private PageConfig createPageConfig(String prefix) {
		PageConfig bean = new PageConfig();
		String pageSizeStr = config.getProperty(new StringBuffer(20).append(
				prefix).append(DOT).append(PAGE_SIZE).toString());
		int pageSize = 0;
		if (StringUtils.isNotBlank(pageSizeStr))
			pageSize = Integer.valueOf(pageSizeStr).intValue();
		bean.setPageSize(pageSize);
		String numPageStr = config.getProperty(new StringBuffer(20).append(
				prefix).append(DOT).append(NUM_PAGES).toString());
		int numPages = 0;
		if (StringUtils.isNotBlank(numPageStr))
			numPages = Integer.valueOf(numPageStr).intValue();
		bean.setNumPagesInCache(numPages);
		String cacheIndStr = config.getProperty(new StringBuffer(prefix)
				.append(DOT).append(CACHE_INDICATOR).toString());
		boolean cacheInd = false;
		if (StringUtils.isNotBlank(cacheIndStr))
			cacheInd = Boolean.valueOf(cacheIndStr).booleanValue();
		bean.setPageCacheInd(cacheInd);
		pageConfigMap.put(prefix, bean);
		return bean;
	}

	/**
	 * This mthod is used to read the config file and store the values
	 * 
	 * @param prefix
	 *            the string whicgh is used as prefix to lookup the properties
	 * @return PageConfig
	 */
	public PageConfig getPageConfigInfo(String prefix) {
		if (prefix == null)
			prefix = PaginationConstants.DEFAULT_PREFIX;
		if (pageConfigMap.containsKey(prefix))
			return (PageConfig) pageConfigMap.get(prefix);
		return createPageConfig(prefix);

	}
}
