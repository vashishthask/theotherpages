package net.sf.theotherpages.business;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.theotherpages.PaginationConstants;
import net.sf.theotherpages.cachestore.PaginationCacheStore;
import net.sf.theotherpages.config.PageConfig;
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
 * <DD>Oct 11, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Oct 11, 2007
 * 
 * @since v1.0, Oct 11, 2007
 * 
 */
public class NonCachedPageDataAccessor implements PageDataAccessor {

	/**
	 * 
	 * This method is used to fetch data from database when caching for
	 * pagination is not enabled. It implicitly go to database to fetch the data
	 * every time (for each page request).
	 * 
	 * <p>
	 * Based on <code>nextPrevPageInd</code> parameter passed, it fetches the
	 * corresponding page from database. It works in following conditions.
	 * <ul>
	 * <li> If <code>nextPrevPageInd</code> asks for the next page, it
	 * provides the <code>PageData</code> data object for next page.
	 * 
	 * <li> If <code>nextPrevPageInd</code> asks for the previous page, it
	 * provides the <code>PageData</code> data object for previous page.
	 * 
	 * <li> If <code>nextPrevPageInd</code> is an integer, it gives the data
	 * for that particular page number. This functionality is used for "go to
	 * page number" functionality.
	 * 
	 * <li> In other cases it returns the <code>PageData</code> object for
	 * first page.
	 * 
	 * </ul>
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
	 * @throws IllegalArgumentException
	 *             thrown in cases where one asks for previous page for first
	 *             page
	 * @see PaginationConstants#NEXTPAGE_IND - Values passed in
	 *      <code>nextPrevPageInd</code>
	 * @see PaginationConstants#PREVIOUSPAGE_IND - Values passed in
	 *      <code>nextPrevPageInd</code>
	 */
	public PageData getPageData(String paginationId, int pageNumber,
			String nextPrevPageInd, PageConfig pageConfig,
			PaginationCacheStore cacheStore) throws Exception {
		int startIndex = 0;
		int endIndex = 0;
		int pageSize = pageConfig.getPageSize();
		if (PaginationConstants.NEXTPAGE_IND.equals(nextPrevPageInd)) {
			startIndex = pageNumber * pageSize;
			endIndex = (pageNumber + 1) * pageSize;
			pageNumber += 1;
		} else if (PaginationConstants.PREVIOUSPAGE_IND.equals(nextPrevPageInd)) {
			pageNumber -= 1;
			startIndex = (pageNumber - 1) * pageSize;
			endIndex = pageNumber * pageSize;
			// handling the case when user click previous on first page
			if (startIndex < 0) {
				throw new IllegalArgumentException(
						"Can't go to previous page for page number 1");
			}
		} else if (StringUtils.isNumeric(nextPrevPageInd)) {
			final int gotoPageNumber = Integer.parseInt(nextPrevPageInd);
			startIndex = (gotoPageNumber - 1) * pageSize;
			endIndex = startIndex + pageSize;
			pageNumber = gotoPageNumber;
		} else {
			startIndex = 0;
			endIndex = pageSize;
			pageNumber = 1;
		}
		return getPageDataFromDataBase(paginationId, startIndex, endIndex,
				pageConfig, pageNumber, cacheStore);

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
	 * @param pageNumber
	 *            current page number
	 * 
	 * @return PageData which contains the result
	 * @throws Exception
	 */
	private PageData getPageDataFromDataBase(String paginationId,
			int startIndex, int endIndex, PageConfig pageConfig,
			int pageNumber, PaginationCacheStore cacheStore) throws Exception {
		DataFetchParams dataFetchParams = (DataFetchParams) cacheStore
				.get(paginationId);
		if (dataFetchParams.getLastPageNumber() != 0
				&& pageNumber > dataFetchParams.getLastPageNumber()) {
			throw new IllegalArgumentException(
					"pageNumber exeeded from last page");
		}

		PaginationCallbackParams params = dataFetchParams.getCallbackParams();
		PaginationCallback pagingCallBack = dataFetchParams.getCallback();
		Object queryParams = null;
		Object[] otherParams = null;
		if (params != null) {
			queryParams = params.getQueryParams();
			otherParams = params.getOtherParams();
		} else
			throw new IllegalStateException(
					"PaginationCallbackParams should not be null");

		List resultList = pagingCallBack.getPaginationData(queryParams,
				otherParams, startIndex, endIndex);
		long numberOfRecords = pagingCallBack.getRecordsCount(queryParams,
				otherParams);

		return createPageData(pageConfig, pageNumber, resultList,
				numberOfRecords);

	}

	private PageData createPageData(PageConfig pageConfig, int pageNumber,
			List resultList, long numberOfRecords) {
		int lastPageNumber = 0;
		lastPageNumber = (int) (numberOfRecords / pageConfig.getPageSize());
		if (numberOfRecords % pageConfig.getPageSize() > 0) {
			lastPageNumber = lastPageNumber + 1;
		}
		return new PageData(pageNumber, resultList, numberOfRecords,
				lastPageNumber);
	}
}
