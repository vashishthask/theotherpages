package net.sf.theotherpages.samples.jspsample;

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
import net.sf.theotherpages.samples.jspsample.util.ListOfData;
import net.sf.theotherpages.service.PaginationService;
import net.sf.theotherpages.service.PaginationServiceImpl;

public class PaginationInAction extends HttpServlet {
	private HttpSession session;
	private PageManager pm;
	private String paginationId = PaginationCallBackImpl.class.getName();
	private PaginationService paginationService = new PaginationServiceImpl(
			new SessionAwareCacheStore(session));
	private PageData pageData = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		session = request.getSession();
		paginationService = new PaginationServiceImpl(
				new SessionAwareCacheStore(session));
		String page = request.getParameter("page");

		try {
			if ("next".equals(page)) {
				getNextPage(request);
			} else if ("previous".equals(page)) {
				getPreviousPage(request);
			} else if (page != null) {
				goToPage(request);
			} else {
				showFirstPage(request);
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("WEB-INF/jsp/Pagination.jsp");
			dispatcher.include(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showFirstPage(HttpServletRequest request) throws Exception {
		pageData = paginationService.getFirstPage(paginationId, null,
				new PaginationCallBackImpl(), null);
		request.setAttribute("pageData", pageData);
	}

	private void getNextPage(HttpServletRequest request) throws Exception {
		pageData = paginationService.getNextPage(paginationId);
		request.setAttribute("pageData", pageData);

	}

	private void getPreviousPage(HttpServletRequest request) throws Exception {
		pageData = paginationService.getPreviousPage(paginationId);
		request.setAttribute("pageData", pageData);

	}

	private void goToPage(HttpServletRequest request) throws Exception {
		int goToPageNumber = Integer.parseInt((String) (request
				.getParameter("page")));
		pageData = paginationService.goToPage(paginationId, goToPageNumber);
		request.setAttribute("pageData", pageData);

	}

	private void goToLastPage(HttpServletRequest request) throws Exception {
		pageData = paginationService.goToLastPage(paginationId);
		request.setAttribute("pageData", pageData);
	}

	class PaginationCallBackImpl implements PaginationCallback {
		ListOfData listOfData = new ListOfData();

		public List getPaginationData(Object queryParams, Object[] otherParams,
				int startIndex, int endIndex) throws Exception {
			return (List) listOfData.getListofData(queryParams, startIndex,
					endIndex);
		}

		public long getRecordsCount(Object queryParams, Object[] otherParams)
				throws Exception {
			return (long) listOfData.getSize();
		}

	}
}