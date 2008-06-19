package net.sf.theotherpages.service;

import net.sf.theotherpages.business.PaginationCallback;
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
public interface PaginationService {

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @param paginationId
	 * @param callbackParams
	 * @param pagingCallBack
	 * @param prefix
	 * @return <code>PageData</code> TODO description
	 * @throws Exception
	 * 
	 */
	public PageData getFirstPage(String paginationId, PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack,
			String prefix) throws Exception;

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * 
	 * @param paginationId
	 * 
	 * @return <code>PageData</code> TODO description
	 * @throws Exception
	 */
	PageData getNextPage(String paginationId) throws Exception;

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * @param paginationId 
	 * 
	 * @return <code>PageData</code> TODO description
	 * @throws Exception 
	 */
	PageData getPreviousPage(String paginationId) throws Exception;

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * @param paginationId 
	 * @param goToPageNumber 
	 * 
	 * @return <code>PageData</code> TODO description
	 * @throws Exception 
	 * 
	 */
	PageData goToPage(String paginationId, int goToPageNumber) throws Exception;

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove
	 * <p>
	 * if not needed.
	 * @param paginationId 
	 * 
	 * @return <code>PageData</code> TODO description
	 * @throws Exception 
	 */
	PageData goToLastPage(String paginationId) throws Exception;

}