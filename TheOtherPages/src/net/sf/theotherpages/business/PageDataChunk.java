package net.sf.theotherpages.business;

import java.util.ArrayList;
import java.util.List;

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
 * <DD>Mar 2, 2007</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Mar 2, 2007
 * 
 * @since v1.0, Mar 2, 2007
 * 
 */
public class PageDataChunk {
	/**
	 * TODO Comment for <code>resultList</code>
	 */
	private List resultList;

	/**
	 * TODO Comment for <code>totalNumRows</code>
	 */
	private long totalNumRows;

	/**
	 * TODO Comment for <code>pageSize</code>
	 */
	private int pageSize;

	/**
	 * TODO Comment for <code>noOfPages</code>
	 */
	private int noOfPages;

	/**
	 * TODO Comment for <code>lastPageInd</code>
	 */
	private boolean lastPageInd = false;

	/**
	 * TODO Comment for <code>firstPageInd</code>
	 */
	private boolean firstPageInd = false;

	/**
	 * TODO Comment for <code>startPageIndex</code>
	 */
	private int startPageIndex;

	/**
	 * TODO Comment for <code>lastPageNumber</code>
	 */
	private int lastPageNumber;


	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove <p> if not needed.
	 * 
	 * @return <code>List</code> TODO description
	 *  
	 */
	public List getFirstPage() {
		return goToPage(1);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove <p> if not needed.
	 * @param currentPageNumber 
	 * @return List
	 */
	public List getNextPage(int currentPageNumber) {
		return goToPage(currentPageNumber + 1);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove <p> if not needed.
	 * @param currentPageNumber 
	 * 
	 * @return <code>List</code> TODO description
	 */
	public List getPreviousPage(int currentPageNumber) {
		return goToPage(currentPageNumber - 1);
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove <p> if not needed.
	 * @param currentPageNumber 
	 * 

	 * @return <code>List</code> TODO description
	 *  
	 */
	public List goToPage(int currentPageNumber) {
		if (currentPageNumber < 1) {
			currentPageNumber = 1;
		}
		List resultDtoList = getResultList();
		if (resultDtoList.size() == 0) {
			return new ArrayList();
		}
		List returnList = new ArrayList();

		int sizeOfPage = getPageSize();
		if (sizeOfPage > resultDtoList.size()) {
			sizeOfPage = resultDtoList.size();
		}
		int startIndex = (currentPageNumber - 1) * sizeOfPage
				% (sizeOfPage * getNoOfPages());
		int resulSize = resultDtoList.size();

		if (startIndex > resulSize)
			return null;
		for (int i = 0; i < sizeOfPage; i++) {
			returnList.add(i, resultDtoList.get(startIndex + i));
		}

		return returnList;
	}

	/**
	 * TODO Mention the description of method over here
	 * <p>
	 * TODO Next paragraph if more than one needed. Otherwise remove <p> if not needed.
	 * 
	 * @return <code>List</code> TODO description
	 */
	public List getAllPages() {
		return getResultList();
	}

	/**
	 * Returns the resultList
	 * @return List
	 */
	public List getResultList() {
		return resultList;
	}

	/**
	 * Sets the resultList
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	/**
	 * Returns the totalNumRows
	 * @return long
	 */
	public long getTotalNumRows() {
		return totalNumRows;
	}

	/**
	 * Sets the totalNumRows
	 * @param totalNumRows The totalNumRows to set.
	 */
	public void setTotalNumRows(long totalNumRows) {
		this.totalNumRows = totalNumRows;
	}

	/**
	 * Returns the pageSize
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the pageSize
	 * @param pageSize The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * Returns the noOfPages
	 * @return int
	 */
	public int getNoOfPages() {
		return noOfPages;
	}

	/**
	 * Sets the noOfPages
	 * @param noOfPages The noOfPages to set.
	 */
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	/**
	 * Returns the lastPageInd
	 * @return boolean
	 */
	public boolean isLastPageInd() {
		return lastPageInd;
	}

	/**
	 * Sets the lastPageInd
	 * @param lastPageInd The lastPageInd to set.
	 */
	public void setLastPageInd(boolean lastPageInd) {
		this.lastPageInd = lastPageInd;
	}

	/**
	 * Returns the firstPageInd
	 * @return boolean
	 */
	public boolean isFirstPageInd() {
		return firstPageInd;
	}

	/**
	 * Sets the firstPageInd
	 * @param firstPageInd The firstPageInd to set.
	 */
	public void setFirstPageInd(boolean firstPageInd) {
		this.firstPageInd = firstPageInd;
	}

	/**
	 * Returns the startPageIndex
	 * @return int
	 */
	public int getStartPageIndex() {
		return startPageIndex;
	}

	/**
	 * Sets the startPageIndex
	 * @param startPageIndex The startPageIndex to set.
	 */
	public void setStartPageIndex(int startPageIndex) {
		this.startPageIndex = startPageIndex;
	}

	/**
	 * Returns the lastPageNumber
	 * @return int
	 */
	public int getLastPageNumber() {
		return lastPageNumber;
	}

	/**
	 * Sets the lastPageNumber
	 * @param lastPageNumber The lastPageNumber to set.
	 */
	public void setLastPageNumber(int lastPageNumber) {
		this.lastPageNumber = lastPageNumber;
	}
}
