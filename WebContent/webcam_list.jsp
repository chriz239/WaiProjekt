<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List of Cameras</title>
</head>
<body>
<center>
	<h1>List of Webcam</h1>
	<table border="0">
  		<tbody>
	  		<tr>	  						
				<td>Name:   </td>
				<td>Url:    </td>
				<td>Id:     </td>	
				<td>   </td>	
				<td>    </td>	
			</tr>
			
			<c:forEach items="${all_users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.vorname}</td>
					<td>${user.nachname}</td>
					<td>
					  <form action="user_mod_view" method="post">
	    			    <button name="userId" value="${user.id}">Ändern</button>
					  </form>
					</td>
					<td>
					  <form action="user_del" method="post">
	    			    <button name="userId" value="${user.id}">Löschen</button>
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
  	<a href="add_new_webcam">Add new Webcam</a>
  	<br>
  	<a href="add_user.jsp">Main menu</a>
</center>
</body>
</html>