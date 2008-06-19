package net.sf.theotherpages.business;

import java.util.List;

import net.sf.theotherpages.PaginationConstants;
import net.sf.theotherpages.cachestore.PaginationCacheStore;
import net.sf.theotherpages.config.PageConfig;
import net.sf.theotherpages.data.DataFetchParams;
import net.sf.theotherpages.data.PageData;
import net.sf.theotherpages.data.PaginationCallbackParams;
import net.sf.theotherpages.util.PaginationUtil;

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
public class CachedPageDataAccessor implements PageDataAccessor {

	private static final String CACHE_DATA_SUFFIX = ".cachedData";

	/**
	 * This method is used to fetch data from cache
	 * 
	 * @param paginationId
	 *            name of the class interface object of service
	 * @param pageNumber
	 *            current page number
	 * @param nextPrevPageInd
	 *            page indicator
	 * @param pageConfig
	 *            bean which contains the information from properties file
	 * @return PageData
	 * @throws Exception
	 */
	public PageData getPageData(String paginationId, int pageNumber,
			String nextPrevPageInd, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		PageDataChunk rptData = (PageDataChunk) cacheStore.get(paginationId
				+ CACHE_DATA_SUFFIX);
		if (PaginationConstants.FISTPAGE_IND.equals(nextPrevPageInd)) {
			return getFirstPage(paginationId, pageNumber, rptData, pageConfig,
					cacheStore);
		} else if (PaginationConstants.NEXTPAGE_IND.equals(nextPrevPageInd)) {
			return getNextPage(paginationId, pageNumber, rptData, pageConfig,
					cacheStore);
		} else if (PaginationConstants.PREVIOUSPAGE_IND.equals(nextPrevPageInd)) {
			return getPreviousPage(paginationId, pageNumber, rptData,
					pageConfig, cacheStore);
		} else {
			pageNumber = Integer.parseInt(nextPrevPageInd);
			return goToPage(paginationId, pageNumber, rptData, pageConfig,
					cacheStore);
		}
	}

	/**
	 * This method is used to fetch results for previous page when cache exists
	 * if requested page is not present in the cache then call to dabase is made
	 * and the cache is refreshed
	 * 
	 * @param paginationId
	 *            the name of the InterfaceObject class
	 * @param pageNumber
	 *            the current page number
	 * @param rptData
	 *            the PageDataCache which contains the results
	 * @param paginationBean
	 * @param pagingCallBack
	 * @return ArrayList of DTOs
	 * @throws Exception
	 */
	private PageData getPreviousPage(String paginationId, int pageNumber,
			PageDataChunk rptData, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		return goToPage(paginationId, (pageNumber - 1), rptData, pageConfig,
				cacheStore);

	}

	/**
	 * This method is used to fetch results for the first page if requested page
	 * is not present in the cache then call to database is made and the cache
	 * is refreshed
	 * 
	 * @param paginationId
	 *            the name of the InterfaceObject class
	 * @param pageNumber
	 *            the current page number
	 * @param pageDataChunk
	 *            the PageDataCache which contains the results
	 * @param paginationBean
	 * @param pagingCallBack
	 * @return ArrayList of DTOs
	 * @throws Exception
	 * @throws Exception
	 */
	private PageData getFirstPage(String paginationId, int pageNumber,
			PageDataChunk pageDataChunk, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		return goToPage(paginationId, 1, pageDataChunk, pageConfig,
				cacheStore);
	}

	/**
	 * This method is used to fetch results for next page when cache exists if
	 * requested page is not present in the cache then call to dabase is made
	 * and the cache is refreshed
	 * 
	 * @param paginationId
	 *            the name of the InterfaceObject class
	 * @param pageNumber
	 *            the current page number
	 * @param pageDataChunk
	 *            the PageDataCache which contains the results
	 * @param pageConfig
	 * @param pagingCallBack
	 * @return arraylist of DTOs
	 * @throws Exception
	 */
	private PageData getNextPage(String paginationId, int pageNumber,
			PageDataChunk pageDataChunk, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		return goToPage(paginationId, (pageNumber + 1), pageDataChunk,
				pageConfig,  cacheStore);
	}

	/**
	 * This method is used to check if the requested page is present in the
	 * cache or not
	 * 
	 * @param pageNumber
	 *            the requested page number
	 * @param pageDataChunk
	 *            the PageDataCache which contains the results
	 * @return boolean which whether the page is is cache or nor
	 */
	private boolean isPageInCache(int pageNumber, PageDataChunk pageDataChunk) {
		if (null != pageDataChunk
				&& pageNumber >= pageDataChunk.getStartPageIndex()
				&& pageNumber <= pageDataChunk.getLastPageNumber()) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to fetch results for a given page when cache exists
	 * if requested page is not present in the cache then call to dabase is made
	 * and the cache is refreshed
	 * 
	 * @param paginationId
	 *            the name of the InterfaceObject class
	 * @param pageNumber
	 *            the current page number
	 * @param pageDataChunk
	 *            the PageDataCache
	 * @param paginationBean
	 * @param pagingCallBack
	 * @return ArrayList of DTOs
	 * @throws Exception
	 */
	private PageData goToPage(String paginationId, int pageNumber,
			PageDataChunk pageDataChunk, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		if (isPageInCache(pageNumber, pageDataChunk)) {
			List records = pageDataChunk.goToPage(pageNumber);
			return new PageData(pageNumber, records, pageDataChunk
					.getNoOfPages(), pageDataChunk.getLastPageNumber());
		} else {
			System.out.println("The startIndex is:"
					+ PaginationUtil.getStartIndex(pageNumber, pageConfig)
					+ " The end index is:"
					+ PaginationUtil.getEndIndex(pageNumber, pageConfig));
			PageDataChunk newCahe = getPageDataChunkFromDataBase(paginationId,
					PaginationUtil.getStartIndex(pageNumber, pageConfig),
					PaginationUtil.getEndIndex(pageNumber, pageConfig),
					pageConfig, cacheStore);
			List records = newCahe.goToPage(pageNumber);
			return new PageData(pageNumber, records, newCahe.getNoOfPages(),
					newCahe.getLastPageNumber());
		}

	}

	/**
	 * This method is used to fetch data from database
	 * 
	 * @param paginationId
	 *            the name of the InterfaceObject class
	 * @param startIndex
	 *            the start index of rows to be fetched
	 * @param endIndex
	 *            the end index of rows to be fetched
	 * @param pageConfig
	 * @param pagingCallBack
	 * 
	 * @return PageDataCache which contains the result
	 * @throws Exception
	 */
	private PageDataChunk getPageDataChunkFromDataBase(String paginationId,
			int startIndex, int endIndex, PageConfig pageConfig,
			PaginationCacheStore cacheStore)
			throws Exception {
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		PaginationCallbackParams callbackParams = dataFetchParams
				.getCallbackParams();
		Object queryParam = null;
		Object[] otherParams = null;
		if (callbackParams != null) {
			queryParam = callbackParams.getQueryParams();
			otherParams = callbackParams.getOtherParams();
		}
		
		PaginationCallback pagingCallBack = dataFetchParams.getCallback();
		System.out.println("The callback is:" + pagingCallBack);
		List resultList = pagingCallBack.getPaginationData(queryParam, otherParams, startIndex,
				endIndex + 1);
		long numberOfRecords = pagingCallBack.getRecordsCount(queryParam, otherParams);

		PageDataChunk pageDataChunk = new PageDataChunk();
		pageDataChunk.setResultList(resultList);
		//pageDataChunk.setNoOfRows(resultList.size());
		pageDataChunk.setPageSize(pageConfig.getPageSize());
		pageDataChunk.setTotalNumRows(numberOfRecords);
		pageDataChunk.setStartPageIndex(PaginationUtil.getStartPageNumber(
				startIndex, pageConfig));
		pageDataChunk.setLastPageNumber(PaginationUtil.getEndPageNumber(endIndex,
				pageConfig));
		if (endIndex + 1 - startIndex > resultList.size()) {
			int lastpage = (int) (numberOfRecords / pageConfig.getPageSize());
			if (numberOfRecords % pageConfig.getPageSize() > 0) {
				lastpage = lastpage + 1;
			}
			pageDataChunk.setLastPageNumber(lastpage);

		}
		
		System.out.println("The page data chunk is:" + pageDataChunk);

		cacheStore.put(paginationId + CACHE_DATA_SUFFIX, pageDataChunk);
		return pageDataChunk;
	}
}