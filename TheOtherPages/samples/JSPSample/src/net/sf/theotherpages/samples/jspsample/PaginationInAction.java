package net.sf.theotherpages.samples.jspsample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.theotherpages.business.PageManager;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.cachestore.SessionAwareCacheStore;
import net.sf.theotherpages.samples.jspsample.util.ListOfData;
import net.sf.theotherpages.service.PaginationService;
import net.sf.theotherpages.service.PaginationServiceImpl;
import net.sf.theotherpages.util.PaginationServiceHttpUtil;

public class PaginationInAction extends HttpServlet {
	private HttpSession session;
	private PageManager pm;
	private String paginationId = PaginationCallBackImpl.class.getName();
	private PaginationService paginationService = new PaginationServiceImpl(
			new SessionAwareCacheStore(session));

	private PaginationServiceHttpUtil httpUtil;
	private static final String PAGE_TO_FORWARD = "/example-pse.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		session = request.getSession();
		paginationService = new PaginationServiceImpl(
				new SessionAwareCacheStore(session));
		httpUtil = new PaginationServiceHttpUtil(paginationService);
		httpUtil.setRequest(request);
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

			request.getRequestDispatcher(PAGE_TO_FORWARD).forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showFirstPage(HttpServletRequest request) throws Exception {
		httpUtil.getFirstPage(paginationId, null, new PaginationCallBackImpl(),
				null);
	}

	private void getNextPage(HttpServletRequest request) throws Exception {
		httpUtil.getNextPage(paginationId);
	}

	private void getPreviousPage(HttpServletRequest request) throws Exception {
		httpUtil.getPreviousPage(paginationId);
	}

	private void goToPage(HttpServletRequest request) throws Exception {
		int goToPageNumber = Integer.parseInt((String) (request
				.getParameter("page")));
		httpUtil.goToPage(paginationId, goToPageNumber);
	}

	private void goToLastPage(HttpServletRequest request) throws Exception {
		httpUtil.goToLastPage(paginationId);
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