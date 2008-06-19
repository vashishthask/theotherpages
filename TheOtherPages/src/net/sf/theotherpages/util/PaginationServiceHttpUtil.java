package net.sf.theotherpages.util;

import javax.servlet.http.HttpServletRequest;

import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.data.PaginationCallbackParams;
import net.sf.theotherpages.service.PaginationService;

public class PaginationServiceHttpUtil {
	PaginationService service;
	
	HttpServletRequest request;
	
	public PaginationServiceHttpUtil(){
		
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public PaginationServiceHttpUtil(PaginationService paginationService){
		service = paginationService;
		
	}
	public PaginationService getService() {
		return service;
	}

	public void setService(PaginationService service) {
		this.service = service;
	}

	public void getFirstPage(String paginationId,
			PaginationCallbackParams callbackParams,
			PaginationCallback pagingCallBack, String prefix) throws Exception {
		request.setAttribute("pageData", service.getFirstPage(paginationId, callbackParams, pagingCallBack, prefix));
	}

	public void getNextPage(String paginationId) throws Exception {
		request.setAttribute("pageData", service.getNextPage(paginationId));
		
	}

	public void getPreviousPage(String paginationId) throws Exception {
		request.setAttribute("pageData", service.getPreviousPage(paginationId));
		
	}

	public void goToLastPage(String paginationId) throws Exception {
		request.setAttribute("pageData", service.goToLastPage(paginationId));
		
	}

	public void goToPage(String paginationId, int goToPageNumber)
			throws Exception {
		request.setAttribute("pageData", service.goToPage(paginationId, goToPageNumber));
		
	}
}