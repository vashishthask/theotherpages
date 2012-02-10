package net.sf.theotherpages.cachestore;

import javax.servlet.http.HttpSession;

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
 * <DD>Oct 19, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @since v1.0
 * 
 */
public class SessionAwareCacheStore implements PaginationCacheStore {

	HttpSession cacheStore;

	/**
	 * Creates a SessionAwareCacheStore with TODO Name of types separated with
	 * comma
	 * <p>
	 * 
	 * @param session
	 * 
	 */
	public SessionAwareCacheStore(HttpSession session) {
		cacheStore = session;
	}

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
		cacheStore.removeAttribute("theotherpages.pages");

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
		return cacheStore.getAttribute(key);
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
		cacheStore.setAttribute(key, value);

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
		cacheStore.removeAttribute(key);
	}

}
