
<%@page import="java.awt.print.Book"%>
<%@page import="com.infinite.LibraryProject.Books"%>
<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Book</title>
</head>
<body>
	<%
		Books book = null;
		if(request.getParameter("bookid") == null){
	%>
	<form action="update-book.jsp">
		<input type="number" name="bookid" placeholder="Book ID"><br>
		<input type="submit" value="SUBMIT">
	</form>
	
	<%
		}
	
		if(request.getParameter("bookid") != null){
			int bookid = Integer.parseInt(request.getParameter("bookid"));
			book = new LibraryDAO().searchBook(bookid);
		}
	%>
	
	<%
		if(book != null && request.getParameter("name") == null){
	%>
		<form action="update-book.jsp">
			<input type="number" name="bookid" value="<%=book.getId() %>" readonly><br>
			<input type="text" name="name" value="<%=book.getName() %>"><br>
			<input type="text" name="author" value="<%=book.getAuthor() %>"><br>
			<input type="text" name="edition" value="<%=book.getEdition() %>"><br>
			<input type="text" name="dept" value="<%=book.getDept() %>"><br>
			<input type="text" name="noOfBooks" value="<%=book.getNoOfBooks() %>"><br>
			<input type="submit" value="SUBMIT">
		</form>
	<%
		}
	
		if(request.getParameter("name") != null){
			book.setId(Integer.parseInt(request.getParameter("bookid")));
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setEdition(request.getParameter("edition"));
			book.setDept(request.getParameter("dept"));
			book.setNoOfBooks(Integer.parseInt(request.getParameter("noOfBooks")));
			out.println(new LibraryDAO().updateBook(book));
		}
	%>
</body>
</html>