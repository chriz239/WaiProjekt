<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List of Cameras</title>
</head>
<body>
<center>
	<h1>List of Webcam</h1>
	<table border="1">
  		<tbody>
	  		<tr>	  						
				<td>Name:   	</td>
				<td>Url:    	</td>
				<td>Id:     	</td>	
				<td>Standort:   </td>		
			</tr>
			
			<c:forEach items="${cam}" var="cam">
				<tr>
					<td><c:out value="${cam.name}"/></td>
					<td><c:out value="${cam.url}"/></td>
					<td><c:out value="${cam.id}"/></td>
					<td><c:out value="${cam.standort}"/></td>
					<td>
					  <form action="cam_edit" method="post">
	    			    <button name="CamId" value="${cam.id}">Edit</button>
					  </form>
					</td>
					<td>
					  <form action="Cam_del" method="post">
	    			    <button name="CamId" value="${cam.id}">Delete</button>
					  </form>
					  </td>
					  <td>
					  <form action="Cam_User_view" method="post">
					  	<button name="Cam_User" value="${user.cam}">Assign</button>
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
  	<a href="add_new_webcam">Add new Webcam</a>
  	<br>
  	<a href="add_user.jsp">Add User</a>
  	</p>
</center>
</body>
</html>