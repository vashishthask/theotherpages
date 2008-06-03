package net.sf.theotherpages.data;

import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

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
 * <DD>Jan 12, 2007</DD>
 * </DL>
 * 
 * @author TCS
 * @author for CVS Inc.
 * @version 1.0, Jan 12, 2007
 * 
 * @since v1.0, Jan 12, 2007
 * 
 */
public class PageData {
	/**
	 * TODO Comment for <code>currentPageNo</code>
	 */
	private int currentPageNo = 0;

	/**
	 * TODO Comment for <code>lastPage</code>
	 */
	private final boolean lastPage = false;

	/**
	 * TODO Comment for <code>firstPage</code>
	 */
	private boolean firstPage = false;

	/**
	 * TODO Comment for <code>pageRecords</code>
	 */
	private List pageRecords = null;

	/**
	 * TODO Comment for <code>noOfRows</code>
	 */
	private long totalNumRows = 0;

	/**
	 * TODO Comment for <code>noOfRows</code
	 */
	private int lastPageNo = 0;

	/**
	 * Creates a PageData with TODO Name of types separated with comma
	 * <p>
	 * @param pageNo 
	 * @param response 
	 * @param totalNumRows 
	 * @param lastPageNumber 
	 * 
	 */
	public PageData(int pageNo, List response, long totalNumRows,
			int lastPageNumber) {
		if (pageNo == 1) {
			firstPage = true;
		}
		this.lastPageNo = lastPageNumber;
		currentPageNo = pageNo;
		pageRecords = response;
		this.totalNumRows = totalNumRows;
	}

	/**
	 * Returns the currentPageNo
	 * 
	 * @return int
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * Returns the firstPage
	 * 
	 * @return boolean
	 */
	public boolean isFirstPage() {
		return firstPage;
	}

	/**
	 * Returns the lastPage
	 * 
	 * @return boolean
	 */
	public boolean isLastPage() {
		if (currentPageNo == lastPageNo) {
			return true;
		}
		return lastPage;
	}

	/**
	 * Returns the noOfRows
	 * 
	 * @return long
	 */
	public long getTotalNumRows() {
		return totalNumRows;
	}

	/**
	 * Returns the pageRecords
	 * 
	 * @return List
	 */
	public List getPageRecords() {
		return pageRecords;
	}

	/**
	 * Returns the lastPageNo
	 * 
	 * @return int
	 */
	public int getLastPageNo() {
		return lastPageNo;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}