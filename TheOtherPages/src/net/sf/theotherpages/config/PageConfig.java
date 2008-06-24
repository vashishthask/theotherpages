package net.sf.theotherpages.config;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class is used to store the values read from the properties file
 * <p>
 * <DL>
 * <DT><B>History: </B>
 * <DD>Dec 29, 2006</DD>
 * </DL>
 * 
 * @author ShriKant
 * @version 1.0, Dec 29, 2006
 * 
 * @since v1.0, Dec 29, 2006
 * 
 */
public class PageConfig {
	private int pageSize;

	private boolean pageCacheInd;

	/**
	 * identifies the number of pages in cache
	 */
	private int numPagesInCache;

	/**
	 * Returns the numPagesInCache
	 * 
	 * @return int
	 */
	public int getNumPagesInCache() {
		return numPagesInCache;
	}

	/**
	 * Sets the numPagesInCache
	 * 
	 * @param numberOfPagesParam
	 *            The numberOfPagesParam to set.
	 */
	public void setNumPagesInCache(int numberOfPagesParam) {
		this.numPagesInCache = numberOfPagesParam;
	}

	/**
	 * Returns the pageCacheInd
	 * 
	 * @return boolean
	 */
	public boolean isCachingRequired() {
		return pageCacheInd;
	}

	/**
	 * Sets the pageCacheInd
	 * 
	 * @param pageCacheIndParam
	 *            The pageCacheIndParam to set.
	 */
	public void setPageCacheInd(boolean pageCacheIndParam) {
		this.pageCacheInd = pageCacheIndParam;
	}

	/**
	 * Returns the pageSize
	 * 
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * Sets the pageSize
	 * 
	 * @param pageSizeParam
	 *            The pageSizeParam to set.
	 */
	public void setPageSize(int pageSizeParam) {
		this.pageSize = pageSizeParam;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

}