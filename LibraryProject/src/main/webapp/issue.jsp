<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.infinite.LibraryProject.ConnectionHelper"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Issue Books</title>
</head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<br>
	<%
		LibraryDAO dao = new LibraryDAO();
		String[] id = request.getParameterValues("bookid");
		String user = (String)session.getAttribute("user");
		
		for(String string : id){
			int bid = Integer.parseInt(string);
			out.println(dao.issueBook(user, bid));
			out.println("<br/>");
		}
	%>
</body>
</html>