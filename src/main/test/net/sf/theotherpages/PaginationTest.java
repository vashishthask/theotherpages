package net.sf.theotherpages;

import java.util.List;

import net.sf.theotherpages.business.PageManager;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.config.Configuration;
import net.sf.theotherpages.data.PageData;

import junit.framework.TestCase;

/**
 * This Class Is Used to Test Pagination ListOfData is used to provide data to
 * Pagination
 */
public class PaginationTest extends TestCase {
	private static final String NEXTPAGE = "nextpage";
	private static final String PREVIOUSPAGE = "previouspage";
	private static final String DEFAULT_CACHE_INDICATOR = "default.cache.indicator";
	private static final String DEFAULT_NUM_PAGES = "default.numPages";
	private static final String DEFAULT_PAGE_SIZE = "default.page.size";
	private PageData pageData;
	private Object queryParam;
	Configuration config;
	private int pagesize;
	private int pagesInCache;
	boolean cacheInd;

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		queryParam = new Object();
		config = Configuration.getInstance();
		pagesize = config.getIntProperty(DEFAULT_PAGE_SIZE);
		pagesInCache = config.getIntProperty(DEFAULT_NUM_PAGES);
		// true for caching and false for not to cache
		cacheInd = config.getBooleanProperty(DEFAULT_CACHE_INDICATOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		pageData = null;
		queryParam = null;
		config = null;
	}

	/**
	 * Method to Test first page.
	 * 
	 * @throws Exception
	 */
	public void testGoToFirstPage() throws Exception {

		// get first PageData
		PageData pageData = getPageData(0, NEXTPAGE, false);

		// test how many rows pagination can cache
		// assertEquals("must be equal", pagesInCache, pageData.getNoOfRows());

		// test for current page .
		assertEquals("must be equal", 1, pageData.getCurrentPageNo());

		// test that return list size is equals to page size specified in config
		// file
		List alist = pageData.getPageRecords();
		assertEquals("must be equal", pagesize, alist.size());

		// get how many rows in source
		ListOfData listOfData = new ListOfData();
		int numOfRecords = listOfData.getSize();
		// get the no. of pages
		int numOffullSizePages = numOfRecords / pagesize;
		int noOfPage;
		if ((numOfRecords % pagesize) > 0) {
			noOfPage = numOffullSizePages + 1;
		} else {
			noOfPage = numOffullSizePages;
		}

		// test whether its feching valid data or not

		for (int x = 0; x < pagesize; x++) {
			List list = pageData.getPageRecords();
			assertEquals("must be equal", "data" + (x + 1), (String) list
					.get(x));
		}

		// test the last page no.
		assertEquals("must be equal", noOfPage, pageData.getLastPageNo());

	}// end of Test Method

	/**
	 * Method to Test nextpage.
	 * 
	 * @throws Exception
	 */
	public void testNextPage() throws Exception {

		// get how many rows in source
		ListOfData listOfData = new ListOfData();
		int numOfRecords = listOfData.getSize();
		// get the no. of pages
		int numOffullSizePages = numOfRecords / pagesize;
		int noOfPage;
		if ((numOfRecords % pagesize) > 0) {
			noOfPage = numOffullSizePages + 1;
		} else {
			noOfPage = numOffullSizePages;
		}

		for (int x = 0; x < noOfPage - 1; x++) {

			PageData pageData = getPageData(x, NEXTPAGE, false);

			// test for current page .
			assertEquals("Current Page No", x + 1, pageData.getCurrentPageNo());
		}

	}

	/**
	 * Method to Test PreviousPage
	 * 
	 * @throws Exception
	 */
	public void testPreviousPage() throws Exception {

		// get how many rows in source
		ListOfData listOfData = new ListOfData();
		int numOfRecords = listOfData.getSize();
		// get the no. of pages
		int numOffullSizePages = numOfRecords / pagesize;
		int noOfPage;
		if ((numOfRecords % pagesize) > 0) {
			noOfPage = numOffullSizePages + 1;
		} else {
			noOfPage = numOffullSizePages;
		}

		for (int x = noOfPage; x > 1; x--) {

			PageData pageData = getPageData(x, PREVIOUSPAGE, false);

			// test for current page .
			assertEquals("Current Page No", x - 1, pageData.getCurrentPageNo());

		}

	}

	/**
	 * Method to Test go to page.
	 * 
	 * @throws Exception
	 */
	public void testGoToPage() throws Exception {

		// get how many rows in source
		ListOfData listOfData = new ListOfData();
		int numOfRecords = listOfData.getSize();
		// get the no. of pages
		int numOffullSizePages = numOfRecords / pagesize;
		int noOfPage;
		if ((numOfRecords % pagesize) > 0) {
			noOfPage = numOffullSizePages + 1;
		} else {
			noOfPage = numOffullSizePages;
		}

		// test when user on 1 page and want to go page no 3
		// first get first page
		PageData pageData = getPageData(0, "nextpage", false);
		int curretPageNum = pageData.getCurrentPageNo();
		// go to 3 page
		if (noOfPage > 2) {
			pageData = getPageData(curretPageNum, "3", false);

			assertEquals("current page", 3, pageData.getCurrentPageNo());

			// test... now user is on 3rd page and go to 1
			pageData = getPageData(3, "1", false);
			assertEquals("current page", 1, pageData.getCurrentPageNo());
		}
		if (noOfPage > 3) {
			// test ..now user is on 1 page and want to go 4 page
			pageData = getPageData(pageData.getCurrentPageNo(), "4", false);
			assertEquals("current page", 4, pageData.getCurrentPageNo());

		}
	}

	/**
	 * Method to Test clearing the cache. Make Sure That:
	 * <p>
	 * 1 .Pagination is configured to use caching. That is:
	 * default.cache.indicator=true.
	 * <p>
	 * 2. There are sufficient numbers of pages >20.
	 * 
	 * @throws Exception
	 */
	public void testClearCache() throws Exception {
		// if cacheInd is true than only Test
		if (cacheInd) {

			// get how many rows in source
			ListOfData listOfData = new ListOfData();
			// reset the hit count to 0
			listOfData.setHitCount(0);
			int numOfRecords = listOfData.getSize();
			// get the no. of pages
			int numOffullSizePages = numOfRecords / pagesize;
			int noOfPage;
			if ((numOfRecords % pagesize) > 0) {
				noOfPage = numOffullSizePages + 1;
			} else {
				noOfPage = numOffullSizePages;
			}

			// first get firstpage with clearCacheInd false. On first Time it
			// will
			// get data from database, in this test it is ListOfData.
			PageData pageData = getPageData(0, "nextpage", false);
			int curretPageNum = pageData.getCurrentPageNo();
			if (noOfPage > 1) {
				// second time it should not be go to database
				pageData = getPageData(curretPageNum, "nextpage", false);

				assertEquals("Hit count", 1, ListOfData.hitCount);
				curretPageNum = pageData.getCurrentPageNo();
			}

			// now clear the cache and go to next page
			if (noOfPage > 2) {
				pageData = getPageData(curretPageNum, "nextpage", true);
				curretPageNum = pageData.getCurrentPageNo();
				// Test that it again hit database or data provider service.
				assertEquals("Hit Count", 2, ListOfData.hitCount);
			}
			if (noOfPage > 3) {
				pageData = getPageData(curretPageNum, "nextpage", true);
				curretPageNum = pageData.getCurrentPageNo();
				// Test that it again hit database or data provider service.
				assertEquals("Hit Count", 3, ListOfData.hitCount);
			}
		}

	}

	/**
	 * This is Helper method used in testing . It encapsulate the implementation
	 * of PaginationCallback interface
	 * 
	 * @param currentPageNo
	 * @param gotoPage
	 * @param clearCacheInd
	 * @return
	 * @throws Exception
	 */
	private PageData getPageData(int currentPageNo, String gotoPage,
			boolean clearCacheInd) throws Exception {
		PageManager manager = PageManager.getInstance();
		PageData pageData = manager.getPageData(ListOfData.class, queryParam,
				currentPageNo, gotoPage, clearCacheInd,

				// Implementation of PaginationCallback interface....start
				new PaginationCallback() {

					public List getPaginationData(Object queryParams,
							int startIndex, int endIndex) throws Exception {

						ListOfData listOfData = new ListOfData();
						return (List) listOfData.getListofData(queryParam,
								startIndex, endIndex);
					}

					public long getRecordsCount(Object queryParams)
							throws Exception {

						ListOfData listOfData = new ListOfData();
						return (long) listOfData.getSize();
					}
				}
		// Implementation of PaginationCallback interface....end
				);

		return pageData;
	}

}
