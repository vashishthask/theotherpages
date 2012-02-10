package net.sf.theotherpages.samples.disptagint.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.theotherpages.business.PaginationCallback;
import net.sf.theotherpages.samples.disptagint.service.ReportService;
import net.sf.theotherpages.util.PaginationServiceHttpUtil;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

public class PaginationInAction extends HttpServlet {
	private String paginationId = PaginationCallBackImpl.class.getName();

	private PaginationServiceHttpUtil httpUtil;
	private static final String PAGE_TO_FORWARD = "/example-pse.jsp";
	private static final String TABLE_ID = "displaytagsample";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		httpUtil = new PaginationServiceHttpUtil(request, paginationId, null,
				new PaginationCallBackImpl(), null);
		String pageParam = new ParamEncoder(TABLE_ID)
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		String pageNumber = request.getParameter(pageParam);

		try {
			if (pageNumber != null) {
				goToPage(pageNumber);
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

	private void goToPage(String pageNumber) throws Exception {
		int goToPageNumber = Integer.parseInt(pageNumber);
		httpUtil.goToPage(goToPageNumber);
	}

	class PaginationCallBackImpl implements PaginationCallback {
		ReportService reportService = new ReportService(50);

		public List getPaginationData(Object queryParams, Object[] otherParams,
				int startIndex, int endIndex) throws Exception {
			return (List) reportService.getListofData(queryParams, startIndex,
					endIndex);
		}

		public long getRecordsCount(Object queryParams, Object[] otherParams)
				throws Exception {
			return (long) reportService.getSize();
		}

	}
}