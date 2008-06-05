package net.sf.theotherpages.samples.swingsample.ui;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.theotherpages.business.PageManager;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.cachestore.SessionAwareCacheStore;
import net.sf.theotherpages.data.PageData;
import net.sf.theotherpages.data.PaginationCallbackParams;
import net.sf.theotherpages.samples.swingsample.util.ListOfData;
import net.sf.theotherpages.service.PaginationService;
import net.sf.theotherpages.service.PaginationServiceImpl;


public class PaginationInAction extends HttpServlet {
	private HttpServletRequest request;
	private HttpSession session;
	private PageManager pm;
	private PaginationCallback pagingCallBack;
	private String paginationId = PaginationCallBackImpl.class.getName();
	private int currentPageNumber = 1;
	private String gotoPageInd = "1";
	private PaginationCallbackParams callbackParams = null;
	private PaginationService paginationService = new PaginationServiceImpl(
			new SessionAwareCacheStore(session));
	private PageData pageData = null;

	public void getSession(HttpServletRequest request) {

		System.out.println("############## getSession ################");
		
		session = request.getSession();


	}
	
	
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		session =request.getSession();
		paginationService = new PaginationServiceImpl(
					new SessionAwareCacheStore(session));
/*		 String next = request.getParameter("next");
		 String previous = request.getParameter("previous");
		 String gotoPage = request.getParameter("gotoPage");*/
		 String page = request.getParameter("page");
		 //System.out.println("next :"+ next + "previous:" + previous +"gotoPage :"+gotoPage);

		 try {
				 if("next".equals(page) ){				
						getNextPage(request);
					}else if("previous".equals(page)){
						getPreviousPage(request);
					}else if(page!=null){
						goToPage(request);
					}else{
						showFirstPage(request);
					}
				 
				 RequestDispatcher dispatcher = request.getRequestDispatcher(
			        "WEB-INF/jsp/Pagination.jsp");
			        dispatcher.include(request, response);          
	 
		 }catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


	public void showFirstPage(HttpServletRequest request) throws Exception {
		getSession(request);
		System.out.println("getFirstPage");
		pageData = paginationService.getFirstPage(paginationId, null, null,
				new PaginationCallBackImpl());
		request.setAttribute("pageData", pageData);
		System.err.println("The datafetchparams are:"+session.getAttribute(paginationId)+"The pagination id is:"+paginationId);
	
	}

	public void  getNextPage(HttpServletRequest request) throws Exception {
		getSession(request);
		System.err.println("The datafetchparams are:"+session.getAttribute(paginationId)+"The pagination id is:"+paginationId);
		System.out.println("getNextPage");
		pageData = paginationService.getNextPage(paginationId);
		request.setAttribute("pageData", pageData);

	}

	public void getPreviousPage(HttpServletRequest request) throws Exception {
		getSession(request);
		System.out.println("getPreviousPage");
		pageData = paginationService.getPreviousPage(paginationId);
		request.setAttribute("pageData", pageData);

	}

	public void goToPage(HttpServletRequest request) throws Exception {
		getSession(request);
		System.out.println("goToPage");
		int goToPageNumber = Integer.parseInt((String)(request.getParameter("page")));
		pageData = paginationService.goToPage(paginationId, goToPageNumber);
		request.setAttribute("pageData", pageData);

	}

	public void goToLastPage(HttpServletRequest request) throws Exception {
		getSession(request);
		System.out.println("go to last page");
		pageData = paginationService.goToLastPage(paginationId);
		request.setAttribute("pageData", pageData);
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
