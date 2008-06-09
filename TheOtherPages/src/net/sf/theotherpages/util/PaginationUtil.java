package net.sf.theotherpages.util;

import net.sf.theotherpages.config.PageConfig;

/**
 * TODO A summary sentence containing a concise but complete description of the API item
 * <p>
 * <b>Overview: </b>
 * <p>
 * TODO Mention the overview of class 
 * using <b>,<p>,<li>,<ui>,<code></code>,<pre></pre>,<i></i>
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
public class PaginationUtil {

	/**
	 * This method is used to retrieve the end index for the dataBase request
	 * 
	 * @param pageNumber
	 *            the page number of the requested page
	 * @param paginationBean
	 * @return the end index for the the database call
	 */
	public static int getEndIndex(int pageNumber, PageConfig paginationBean) {
		// int[] pageInfo= getPageSizesInfo(prefix);
		int k = pageNumber / (paginationBean.getNumPagesInCache() + 1);
		int endIndex = (k + 1) * paginationBean.getNumPagesInCache()
				* paginationBean.getPageSize();
		return endIndex;
	}

	/**
	 * This method is used to retrieve the end index for the dataBase request
	 * 
	 * @param pageNumber
	 *            the page number of the requested page
	 * @param pageConfig
	 * @return the start index for the the database call
	 */
	public static int getStartIndex(int pageNumber, PageConfig pageConfig) {

		int k = pageNumber / (pageConfig.getNumPagesInCache() + 1);
		int startIndex = k * pageConfig.getNumPagesInCache()
				* pageConfig.getPageSize();
		return startIndex;
	}

	/**
	 * This method is used to find the end page number
	 * 
	 * @param endIndex
	 *            the end index of the request
	 * @param paginationBean
	 * @return the end page number
	 */
	public static int getEndPageNumber(int endIndex, PageConfig paginationBean) {
		return endIndex / paginationBean.getPageSize();
	}

	/**
	 * This method is used to get the starting page number
	 * 
	 * @param startIndex
	 *            the start index of the request
	 * @param pageConfig
	 * @return the starting page number
	 */
	public static int getStartPageNumber(int startIndex,
			PageConfig pageConfig) {
		return (startIndex / pageConfig.getPageSize()) + 1;
	}

}
