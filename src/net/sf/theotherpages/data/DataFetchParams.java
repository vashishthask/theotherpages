package net.sf.theotherpages.data;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.sf.theotherpages.business.PaginationCallback;

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
public class DataFetchParams {

	/**
	 * TODO Comment for <code>callback</code>
	 */
	PaginationCallback callback;
	/**
	 * TODO Comment for <code>callbackParams</code>
	 */
	PaginationCallbackParams callbackParams;

	/**
	 * TODO Comment for <code>currentPageNumber</code>
	 */
	int currentPageNumber;
	/**
	 * TODO Comment for <code>lastPageNumber</code>
	 */
	int lastPageNumber;

	/**
	 * Returns the callback
	 * 
	 * @return PaginationCallback
	 */
	public PaginationCallback getCallback() {
		return callback;
	}

	/**
	 * Sets the callback
	 * 
	 * @param callback
	 *            The callback to set.
	 */
	public void setCallback(PaginationCallback callback) {
		this.callback = callback;
	}

	/**
	 * Returns the callbackParams
	 * 
	 * @return PaginationCallbackParams
	 */
	public PaginationCallbackParams getCallbackParams() {
		return callbackParams;
	}

	/**
	 * Sets the callbackParams
	 * 
	 * @param callbackParams
	 *            The callbackParams to set.
	 */
	public void setCallbackParams(PaginationCallbackParams callbackParams) {
		this.callbackParams = callbackParams;
	}

	/**
	 * Creates a DataFetchParams with TODO Name of types separated with comma
	 * <p>
	 * @param callback 
	 * @param callbackParams 
	 * 
	 */
	public DataFetchParams(PaginationCallback callback,
			PaginationCallbackParams callbackParams) {
		this.callback = callback;
		this.callbackParams = callbackParams;
	}

	/**
	 * Returns the currentPageNumber
	 * 
	 * @return int
	 */
	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	/**
	 * Sets the currentPageNumber
	 * 
	 * @param currentPageNumber
	 *            The currentPageNumber to set.
	 */
	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	/**
	 * Returns the lastPageNumber
	 * 
	 * @return int
	 */
	public int getLastPageNumber() {
		return lastPageNumber;
	}

	/**
	 * Sets the lastPageNumber
	 * 
	 * @param lastPageNumber
	 *            The lastPageNumber to set.
	 */
	public void setLastPageNumber(int lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}
