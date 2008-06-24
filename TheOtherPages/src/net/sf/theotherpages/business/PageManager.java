package net.sf.theotherpages.business;

import net.sf.theotherpages.PaginationConstants;
import net.sf.theotherpages.cachestore.AppLevelCacheStore;
import net.sf.theotherpages.cachestore.PaginationCacheStore;
import net.sf.theotherpages.config.Configuration;
import net.sf.theotherpages.config.PageConfig;
import net.sf.theotherpages.data.DataFetchParams;
import net.sf.theotherpages.data.PageData;
import net.sf.theotherpages.data.PaginationCallbackParams;

/**
 * This is the main class for the pagination framework
 * <p>,
 * <li>,<ui>,<code></code>,
 * 
 * <pre></pre>,<i></i>
 * 
 * <p>
 * <DL>
 * <DT><B>History: </B>
 * <DD>Dec 26, 2006</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Dec 26, 2006
 * 
 * @since v1.0, Dec 26, 2006
 * 
 */
public class PageManager {

	PaginationCacheStore cacheStore;

	/**
	 * Creates a PageManager with TODO Name of types separated with comma
	 * <p>
	 * 
	 * @param cacheStore
	 * 
	 */
	public PageManager(PaginationCacheStore cacheStore) {
		if (cacheStore != null)
			this.cacheStore = cacheStore;
		else
			new PageManager();
	}

	/**
	 * Creates a PageManager with TODO Name of types separated with comma
	 */
	public PageManager() {
		this.cacheStore = new AppLevelCacheStore();
	}

	/**
	 * @param paginationId
	 *            the name of the dao
	 * @param currentPageNumber
	 *            the current page number for the request
	 * @param gotoPageInd
	 *            indicator for next , previous or some page number
	 * @param callbackParams
	 * @param pagingCallBack
	 * @return PageData
	 * 
	 * @throws Exception
	 */
	public PageData getPageData(String paginationId, int currentPageNumber,
			String gotoPageInd, PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack) throws Exception {
		return getPageData(paginationId, currentPageNumber, gotoPageInd,
				callbackParams, pagingCallBack,
				PaginationConstants.DEFAULT_PREFIX);
	}

	/**
	 * 
	 * @param paginationId
	 *            the name of the dao
	 * @param currentPageNumber
	 *            the current page number for the request
	 * @param gotoPageInd
	 *            indicator for next , previous or some page number
	 * @param callbackParams
	 * @param pagingCallBack
	 * @param prefix
	 *            used for getting prefixed values from the properties file
	 * @return PageData
	 * @throws Exception
	 */
	public PageData getPageData(String paginationId, int currentPageNumber,
			String gotoPageInd, PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack, String prefix) throws Exception {

		if (pagingCallBack != null) {
			cacheStore.put(paginationId, new DataFetchParams(pagingCallBack,
					callbackParams));
		}

		PageConfig pageConfig = Configuration.getInstance().getPageConfigInfo(
				prefix);

		PageDataAccessor pageDataAccessor = createPageDataAccessor(pageConfig);

		PageData pageData = pageDataAccessor.getPageData(paginationId,
				currentPageNumber, gotoPageInd, pageConfig, cacheStore);

		updateDataFetchParams(paginationId, pageData);

		return pageData;
	}

	private void updateDataFetchParams(String paginationId, PageData pageData) {
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		dataFetchParams.setCurrentPageNumber(pageData.getCurrentPageNo());
		dataFetchParams.setLastPageNumber(pageData.getLastPageNo());
		cacheStore.put(paginationId, dataFetchParams);
	}

	private PageDataAccessor createPageDataAccessor(PageConfig pageConfig) {
		if (pageConfig.isCachingRequired())
			return new CachedPageDataAccessor();
		return new NonCachedPageDataAccessor();
	}
}