<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="models.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add WebCam</title>
</head>
<body>
	<center>
	<h1>Add WebCam</h1>
	<form name="form_add_Webcam" action="AddCam" method="post">		
		<table border="0">
			<tbody>
				<tr>
					<td>Name</td>
					<td><input type="text" name="camName" value="" style="width: 300px"></td>		
				</tr>
				<tr>
					<td>URL</td>
					<td><input type="text" name="camUri" value="" style="width: 300px"></td>		
				</tr>
				<tr>	
					<td colspan="2"><center><input type="submit" name="btnAdd" value="Add"></center></td>
				</tr>				
			</tbody>
		</table>
	</form>
	<br>
	<p><a href="navigation_menu.jsp">Navigation menu</a></p>
	</center>
</body>
</html>