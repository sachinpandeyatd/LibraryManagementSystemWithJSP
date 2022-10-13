<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
</head>
<body>
	<h1>Add User</h1>
	<form action="add-user.jsp" method="post">
		Enter User Name:
		<input type="text" name="Username" /><br/><br/>
		Password:
		<input type="password" name="Password" /><br/><br/>
		<input type="submit" value="Submit" />
	</form>
	<%
		if(request.getParameter("Password")!=null){
			String Username = request.getParameter("Username");
			String Password = request.getParameter("Password");
			LibraryDAO dao = new LibraryDAO();
			dao.AddUser(Username,Password);
		}
	%>

</body>
</html>