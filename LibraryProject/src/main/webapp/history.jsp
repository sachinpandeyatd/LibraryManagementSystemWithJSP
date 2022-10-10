<%@page import="java.util.List"%>
<%@page import="com.infinite.LibraryProject.TranBook"%>
<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Return History</title>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<br>
	
	<%
		List<TranBook> booksList = new LibraryDAO().returnHistory((String)session.getAttribute("user"));
	%>
		<table border="3">
		<tr>
			<th>Book Id</th>
			<th>User Name</th>
			<th>Issued On</th>
			<th>Returned On</th>
		</tr>
	<%
		for(TranBook history : booksList) {
	%>
		<tr>
			<td><%=history.getBookId() %> </td>
			<td><%=history.getUserName() %> </td>
			<td><%=history.getFromDate() %> </td>
			<td><%=history.getToDate() %> </td>
		</tr>
	<%
		}
	%>
	</table>
</body>
</html>