package net.sf.theotherpages.cachestore;

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
 * <DD>Oct 10, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Oct 10, 2007
 * 
 * @since v1.0, Oct 10, 2007
 * 
 */
public interface PaginationCacheStore {

	/**
	 * Get an item from the cache store
	 * 
	 * @param key
	 * @return the cached object or <tt>null</tt>
	 */
	public Object get(String key);

	/**
	 * Add an item to the cache store
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);

	/**
	 * Remove an item from the cache store
	 * @param key 
	 */
	public void remove(String key);

	/**
	 * Clear the cache store
	 */
	public void clear();
}