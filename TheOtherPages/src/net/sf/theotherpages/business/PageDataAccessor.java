package net.sf.theotherpages.business;

import net.sf.theotherpages.cachestore.PaginationCacheStore;
import net.sf.theotherpages.config.PageConfig;
import net.sf.theotherpages.data.PageData;

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
 * <DD>Oct 16, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @since v1.0
 * 
 */
public interface PageDataAccessor {

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * @param paginationId 
	 * @param pageNumber 
	 * @param nextPrevPageInd 
	 * @param pageConfig 
	 * @param cacheStore 
	 * @return PageData
	 * @throws Exception 
	 */
	PageData getPageData(String paginationId, int pageNumber,
			String nextPrevPageInd, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception;
}
