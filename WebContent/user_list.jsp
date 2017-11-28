<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>User List</title>
</head>

<body>
<center>
	<h1>User List</h1>
	<table border="1">
  		<tbody>
	  		<tr>	  						
				<td>Name:</td>
				<td>Löschen:</td>	
				<td>Webcam Zuweisung:</td>	
			</tr>
			
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.name}"/></td>
					<td>
						<a href="UserMod?action=delete&userId=${user.id}">Löschen</a>
					</td>
					<td>
						<a href="UserMod?action=cams&userId=${user.id}">Cams</a> 
					  </td>
					  </tr>
			</c:forEach>
  		</tbody>
  	</table>
  	<br>
  	<p>
  	<!-- <a href="webcam_list.jsp">assign Webcam to User</a> -->
  	<br>
  	<a href="AddUser">Add User</a>
  	<br>
  	<p>
  	<a href="navigation_menu.jsp">Navigation menu</a>
</center>
</body>
</html>