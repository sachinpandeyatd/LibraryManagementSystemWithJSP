<%@page import="com.infinite.LibraryProject.LibraryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="login.jsp" method="post">
			<input type="text" name="username" placeholder="Username"><br><br>
			<input type="password" name="password" placeholder="Password"><br><br>
			<input type="submit" value="SUBMIT">
	</form>
	
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username != null && password != null){
			LibraryDAO dao = new LibraryDAO();
			int count = dao.authenticate(username, password);
			
			if(count != 0){		
				session.setAttribute("user", username);
				%>
					<jsp:forward page="menu.jsp" />
				<%			
			}else{
				out.print("Invalid Credentials, check and please try again.");
			}
		}
	%>
</body>
</html>