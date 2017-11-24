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
				<td>Name:   	</td>
				<td>Id:     	</td>	
				<td>Webcam:		</td>	
			</tr>
			
			<c:forEach items="${user}" var="User">
				<tr>
					<td><c:out value="${user.name}"/></td>
					<td><c:out value="${user.id}"/></td>
					<td><c:out value="${user.cam}"/></td>
					<td>
					  <form action="user_edit" method="post">
	    			    <button name="UserID" value="${user.id}">Edit</button>
					  </form>
					</td>
					<td>
					  <form action="user_del" method="post">
	    			    <button name="UserID" value="${user.id}">Delete</button>
					  </form>
					  </td>
					  </tr>
			</c:forEach>	  	
			<tr>
				<td></td>
			</tr>		
  		</tbody>
  	</table>
  	<br>
  	<p>
  	<a href="webcam_list">assign Webcam to User</a>
  	<br>
  	<a href="add_user.jsp">Add User</a>
  	</p>
</center>
</body>
</html>