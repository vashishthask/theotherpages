<jsp:root version="1.2" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:display="urn:jsptld:http://displaytag.sf.net">
  <jsp:directive.page contentType="text/html; charset=UTF-8" />
  <jsp:directive.page import="net.sf.theotherpages.samples.disptagint.data.*, net.sf.theotherpages.data.*, org.displaytag.util.*, org.displaytag.tags.*" />
  <jsp:include page="inc/header.jsp" flush="true" />
  <jsp:scriptlet> request.setAttribute( "test", new ReportList() ); </jsp:scriptlet>
  <jsp:scriptlet> 
  	PageData pageData = (PageData)request.getAttribute( "pageData");
  	java.util.List pageList = pageData.getPageRecords();
  	request.setAttribute("pageList", pageList);
  	int size = (int)pageData.getTotalNumRows();
  	int pageSize = 3;
  	
  	//request.setAttribute("pageSize", new Integer(pageSize));
  	request.setAttribute("resultSize", new Integer(size));
  	//System.out.println("The size****:"+   (Integer.parseInt(request.getParameter((new ParamEncoder("49653").encodeParameterName(TableTagParameters.PARAMETER_PAGE)))) - 1) * pageSize); 
  	System.out.println("The parameter map is:"+request.getParameterMap());
  </jsp:scriptlet>
  <h2>Paging + sorting + grouping + exporting working together</h2>

  <display:table name="pageList" export="true" sort="external" pagesize="3" id="displaytagsample" requestURI="/PaginationInAction" partialList="true" size="resultSize">
    <display:column property="city" title="CITY" group="1" sortable="true" sortName="city" headerClass="sortable" />
    <display:column property="project" title="PROJECT" group="2" sortable="true" headerClass="sortable" />
    <display:column property="amount" title="HOURS" />
    <display:column property="task" title="TASK" />
  </display:table>
  
  <jsp:scriptlet>
  	String param = new ParamEncoder("member").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
  	System.out.println("The size****:"+   param); 
  	//String size2= request.getParameter(param);
  	//if (size2 != null)
  	  //System.out.println("The size****:"+   (Integer.parseInt(size2)) - 1) * pageSize); 
  	  //System.out.println("The size****:"+   size2); 
  </jsp:scriptlet>


  <p>What happen if you put everything together, sorting the full list (attribute <code>sort="list"</code>), using
  pagination and exporting?</p>

  <p>When sorting is enabled on the full list, the page number is automatically reset if you change the sorted column or
  the sort order.</p>

  <p>Exported documents contain the full list. Grouping is not applied in the exported documents.</p>



  <jsp:include page="inc/footer.jsp" flush="true" />

</jsp:root>
