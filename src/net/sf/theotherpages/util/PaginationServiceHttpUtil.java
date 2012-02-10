package net.sf.theotherpages.util;

import javax.servlet.http.HttpServletRequest;

import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.cachestore.SessionAwareCacheStore;
import net.sf.theotherpages.data.PaginationCallbackParams;
import net.sf.theotherpages.service.PaginationService;
import net.sf.theotherpages.service.PaginationServiceImpl;

public class PaginationServiceHttpUtil {
	PaginationService service;
	HttpServletRequest request;
	
	public PaginationServiceHttpUtil(HttpServletRequest request, String paginationId,
			PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack, String prefix) {
		this.request = request;
		this.service = new PaginationServiceImpl(new SessionAwareCacheStore(request.getSession(false)), paginationId, callbackParams, pagingCallBack, prefix);
	}

	public PaginationService getService() {
		return service;
	}

	public void setService(PaginationService service) {
		this.service = service;
	}

	public void getFirstPage() throws Exception {
		request.setAttribute("pageData", service.getFirstPage());
	}

	public void getNextPage() throws Exception {
		request.setAttribute("pageData", service.getNextPage());
		
	}

	public void getPreviousPage() throws Exception {
		request.setAttribute("pageData", service.getPreviousPage());
		
	}

	public void goToLastPage() throws Exception {
		request.setAttribute("pageData", service.goToLastPage());
		
	}

	public void goToPage(int goToPageNumber)
			throws Exception {
		request.setAttribute("pageData", service.goToPage(goToPageNumber));
		
	}
}