package net.sf.theotherpages.service;

import net.sf.theotherpages.PaginationConstants;
import net.sf.theotherpages.business.PageManager;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.cachestore.PaginationCacheStore;
import net.sf.theotherpages.data.DataFetchParams;
import net.sf.theotherpages.data.PageData;
import net.sf.theotherpages.data.PaginationCallbackParams;

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
public class PaginationServiceImpl implements PaginationService {

	PaginationCacheStore cacheStore;

	/**
	 * Creates a PaginationServiceImpl with <code>cacheStore</code> param.
	 * @param cacheStore
	 * 
	 */
	public PaginationServiceImpl(PaginationCacheStore cacheStore) {
		this.cacheStore = cacheStore;
	}

	/**
	 * Creates a default <code>PaginationServiceImpl</code> instance.
	 */
	public PaginationServiceImpl() {
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.service.PaginationService#getFirstPage(java.lang.String,
	 *      net.sf.theotherpages.data.PaginationCallbackParams,
	 *      net.sf.theotherpages.business.PaginationCallback, java.lang.String)
	 */
	public PageData getFirstPage(String paginationId,
			PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack, String prefix) throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		return pageManager.getPageData(paginationId, 0,
				PaginationConstants.FISTPAGE_IND, callbackParams,
				pagingCallBack, prefix);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.service.PaginationService#getNextPage(java.lang.String)
	 */
	public PageData getNextPage(String paginationId) throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		int currentPageNumber = dataFetchParams.getCurrentPageNumber();
		return pageManager.getPageData(paginationId, currentPageNumber,
				PaginationConstants.NEXTPAGE_IND, null, null);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.service.PaginationService#getPreviousPage(java.lang.String )
	 */
	public PageData getPreviousPage(String paginationId) throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		int currentPageNumber = dataFetchParams.getCurrentPageNumber();
		return pageManager.getPageData(paginationId, currentPageNumber,
				PaginationConstants.PREVIOUSPAGE_IND, null, null);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.service.PaginationService#goToLastPage(java.lang.String)
	 */
	public PageData goToLastPage(String paginationId) throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		int currentPageNum = dataFetchParams.getCurrentPageNumber();
		int lastPageNum = dataFetchParams.getLastPageNumber();

		return pageManager.getPageData(paginationId, currentPageNum, String
				.valueOf(lastPageNum), null, null);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @see net.sf.theotherpages.service.PaginationService#goToPage(java.lang.String,
	 *      int)
	 */
	public PageData goToPage(String paginationId, int goToPageNumber)
			throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		int currentPageNum = dataFetchParams.getCurrentPageNumber();
		return pageManager.getPageData(paginationId, currentPageNum, String
				.valueOf(goToPageNumber), null, null);
	}

	/**
	 * Returns the cacheStore
	 * 
	 * @return PaginationCacheStore
	 */
	public PaginationCacheStore getCacheStore() {
		return cacheStore;
	}

	/**
	 * Sets the cacheStore
	 * 
	 * @param cacheStore
	 *            The cacheStore to set.
	 */
	public void setCacheStore(PaginationCacheStore cacheStore) {
		this.cacheStore = cacheStore;
	}
}
