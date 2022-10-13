<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@page import="com.infinite.LibraryProject.Books"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
	<form action="">
		Book Id:
		<input type="number" name="id"/><br/>
		Book Name:
		<input type="text" name="name"/><br/>
		Book Author:
		<input type="text" name="author"/><br/>
		Book Edition:
		<input type="text" name="edition"/><br/>
		Book Department:
		<input type="text" name="dept"/><br/>
		Book NO of Books:
		<input type="number" name="noOfBooks"/><br/>
		<input type="submit" value="Add"/><br/><br/>
	</form>
	<%
		if(request.getParameter("id")!=null){
			Books book= new Books();
			book.setId(Integer.parseInt(request.getParameter("id")));
			book.setName(request.getParameter("name"));
			book.setAuthor(request.getParameter("author"));
			book.setEdition(request.getParameter("edition"));
			book.setDept(request.getParameter("dept"));
			book.setNoOfBooks(Integer.parseInt(request.getParameter("noOfBooks")));
			LibraryDAO dao=new LibraryDAO();
			dao.AddBook(book);
		}
	%>
</body>
</html>