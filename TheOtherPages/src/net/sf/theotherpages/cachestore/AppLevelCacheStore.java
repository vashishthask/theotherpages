package net.sf.theotherpages.cachestore;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO A summary sentence containing a concise but complete description of the
 * API item
 * <p>
 * <b>Overview: </b>
 * <p>
 * TODO Mention the overview of class using <b>,
 * <p>,
 * <li>,<ui>,<code></code>,
 * 
 * <pre></pre>,<i></i>
 * 
 * <p>
 * <DL>
 * <DT><B>History: </B>
 * <DD>Oct 11, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Oct 11, 2007
 * 
 * @since v1.0, Oct 11, 2007
 * 
 */
public class AppLevelCacheStore implements PaginationCacheStore {

	private static Map cacheStore = new HashMap();

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.cachestore.PaginationCacheStore#clear()
	 */
	public void clear() {
		cacheStore.clear();
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.cachestore.PaginationCacheStore#get(java.lang.String)
	 */
	public Object get(String key) {
		return cacheStore.get(key);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.cachestore.PaginationCacheStore#put(java.lang.String,
	 *      java.lang.Object)
	 */
	public void put(String key, Object value) {
		cacheStore.put(key, value);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.cachestore.PaginationCacheStore#remove(java.lang.String)
	 */
	public void remove(String key) {
		cacheStore.remove(key);
	}
}