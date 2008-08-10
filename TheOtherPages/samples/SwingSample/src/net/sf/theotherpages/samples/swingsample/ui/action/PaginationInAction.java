package net.sf.theotherpages.samples.swingsample.ui.action;

import java.util.List;

import net.sf.theotherpages.business.PageManager;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.cachestore.AppLevelCacheStore;
import net.sf.theotherpages.data.PageData;
import net.sf.theotherpages.data.PaginationCallbackParams;
import net.sf.theotherpages.samples.swingsample.util.ListOfData;
import net.sf.theotherpages.service.PaginationService;
import net.sf.theotherpages.service.PaginationServiceImpl;


public class PaginationInAction{

	private PageManager pm;
	private PaginationCallback pagingCallBack;
	private String paginationId = PaginationCallBackImpl.class.getName();
	private int currentPageNumber = 1;
	private String gotoPageInd = "1";
	private PaginationCallbackParams callbackParams = null;
	private PaginationService paginationService; 
	private PageData pageData = null;

	public void init() {

		paginationService = new PaginationServiceImpl(
				new AppLevelCacheStore(), paginationId, null, 
				new PaginationCallBackImpl(), null);

	}

	public PageData getFirstPage() throws Exception {
		init();
		System.out.println("getFirstPage");
		pageData = paginationService.getFirstPage();
		
		return pageData;
	}

	public PageData getNextPage() throws Exception {
		init();		
		System.out.println("getNextPage");
		pageData = paginationService.getNextPage();
		return pageData;
	}

	public PageData getPreviousPage() throws Exception {
		init();
		System.out.println("getPreviousPage");
		pageData = paginationService.getPreviousPage();

		return pageData;
	}

	public PageData goToPage(int goToPageNumber) throws Exception {
		init();
		System.out.println("goToPage");
		pageData = paginationService.goToPage(goToPageNumber);

		return pageData;
	}

	public PageData goToLastPage() throws Exception {
		init();
		System.out.println("go to last page");
		pageData = paginationService.goToLastPage();
		return pageData;
	}

	class PaginationCallBackImpl implements PaginationCallback {

		public List getPaginationData(Object queryParams, Object[] otherParams,
				int startIndex, int endIndex) throws Exception {
			ListOfData listOfData = new ListOfData();
			return (List) listOfData.getListofData(queryParams, startIndex,
					endIndex);
		}

		public long getRecordsCount(Object queryParams, Object[] otherParams)
				throws Exception {

			ListOfData listOfData = new ListOfData();
			return (long) listOfData.getSize();
		}

	}

}
