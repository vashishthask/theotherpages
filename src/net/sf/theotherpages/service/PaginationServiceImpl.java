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
	String paginationId;
	PaginationCallbackParams callbackParams;
	PaginationCallback pagingCallBack; 
	String prefix;	

	/**
	 * Creates a PaginationServiceImpl with <code>cacheStore</code> param.
	 * @param cacheStore
	 * @param paginationId
	 * @param callbackParams 
	 * @param pagingCallBack 
	 * @param prefix 
	 * 
	 */
	public PaginationServiceImpl(PaginationCacheStore cacheStore, String paginationId,
			PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack, String prefix) {
		this.cacheStore = cacheStore;
		this.paginationId = paginationId;
		this.callbackParams = callbackParams;
		this.pagingCallBack = pagingCallBack;
		this.prefix = prefix;
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
	public PageData getFirstPage() throws Exception {
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
	public PageData getNextPage() throws Exception {
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
	public PageData getPreviousPage() throws Exception {
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
	public PageData goToLastPage() throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		if(dataFetchParams == null)
			return getFirstPage();
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
	public PageData goToPage(int goToPageNumber)
			throws Exception {
		PageManager pageManager = new PageManager(cacheStore);
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		if(dataFetchParams == null)
			throw new IllegalStateException("Illegal state of cachestore. Please ");
		int currentPageNum = dataFetchParams.getCurrentPageNumber();
		return pageManager.getPageData(paginationId, currentPageNum, String
				.valueOf(goToPageNumber), null, null);
	}
}