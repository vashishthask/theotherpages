package net.sf.theotherpages.samples.jspsample;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.theotherpages.PaginationConstants;
import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.samples.jspsample.util.ListOfData;
import net.sf.theotherpages.util.PaginationServiceHttpUtil;

public class PaginationInAction extends HttpServlet {
	private static final String REQUEST_PARAM_PAGE = "page";
	private HttpSession session;
	private String paginationId = PaginationCallBackImpl.class.getName();
	
	private PaginationServiceHttpUtil httpUtil;
	private static final String PAGE_TO_FORWARD = "/WEB-INF/jsp/Pagination.jsp";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		session = request.getSession();
		httpUtil = new PaginationServiceHttpUtil(request, paginationId, null,
				new PaginationCallBackImpl(), null);
		String page = request.getParameter(REQUEST_PARAM_PAGE);

		try {
			if (PaginationConstants.NEXTPAGE_IND.equals(page)) {
				getNextPage(request);
			} else if (PaginationConstants.PREVIOUSPAGE_IND.equals(page)) {
				getPreviousPage(request);
			} else if (page != null) {
				goToPage(page);
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
		httpUtil.getFirstPage();
	}

	private void getNextPage(HttpServletRequest request) throws Exception {
		httpUtil.getNextPage();
	}

	private void getPreviousPage(HttpServletRequest request) throws Exception {
		httpUtil.getPreviousPage();
	}

	private void goToPage(String pageNumber) throws Exception {
		int goToPageNumber = Integer.parseInt(pageNumber);
		httpUtil.goToPage(goToPageNumber);
	}

	private void goToLastPage(HttpServletRequest request) throws Exception {
		httpUtil.goToLastPage();
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