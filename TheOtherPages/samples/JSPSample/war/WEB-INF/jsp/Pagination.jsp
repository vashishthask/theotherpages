<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="net.sf.theotherpages.data.PageData"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="PaginationInAction" method="get">
<table>
	<tr>
	<c:if test="${!pageData.firstPage}">
	    <td>
	    <a href="PaginationInAction?page=previous" onclick="this.form.submit();"><</a>
	    </td>
	</c:if>
	
	
	<td>
	<select name="page" onchange="this.form.submit();">

	 <c:forEach  begin ="1" end="${pageData.lastPageNo}" var="item">
       <option value="${item}" ${item == pageData.currentPageNo ? "selected" : ""}>${item}</option>
     </c:forEach>
     </select>
	</td>
	
	
	<c:if test="${!pageData.lastPage}">
		<td>
		<a href="PaginationInAction?page=next" onclick="this.form.submit();">></a>
		</td>
    </c:if>
    <td> 
    <td>     
	</tr>
	
</table>
<p>
</form>


<table border="">
<c:forEach var="data" items="${pageData.pageRecords}">
		<tr>
			<td><c:out value="${data}"></c:out>
		</tr>
</c:forEach>
</table>


</body>
</html>