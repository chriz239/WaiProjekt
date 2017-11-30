<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="models.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Edit Webcams for user</title>
</head>

<body>
	<center>
		<h1>Webcam Access</h1>
		<table border="1">
			<tbody>
				<tr>
					<td>Cam Name:</td>
					<td>Hinzufügen:</td>
					<td>Entfernen:</td>
				</tr>
				<c:forEach items="${cams}" var="cam">
					<tr>
						<form action="UserMod" method="post">
							<input type="hidden" name="userId" value="${userId}"> <input
								type="hidden" name="camId" value="${cam.id}">
							<td><c:out value="${cam.name}" /></td>
							<td><input type="submit" name="mod" value="add"></td>
							<td><input type="submit" name="mod" value="remove"></td>
						</form>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<br>
		<p>
			<!-- <a href="webcam_list.jsp">assign Webcam to User</a> -->
			<br> <a href="AddUser">Add User</a> <br>
		<p>
			<a href="navigation_menu.jsp">Navigation menu</a>
	</center>
</body>
</html>