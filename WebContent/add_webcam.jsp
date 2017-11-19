<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit WebCam</title>
</head>
<body>
	<center>
	<h1>Add WebCam</h1>
	<form name="form_user_edit" action="cam_mod" method="post">		
		<table border="0">
			<tbody>
				<tr>
					<td>URL</td>
					<td><input type="text" name="camUri" value="" style="width: 300px"></td>		
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="camName" value="" style="width: 300px"></td>		
				</tr>
				<tr>	
					<td colspan="2"><center><input type="submit" name="btnAdd" value="Add"></center></td>
				</tr>				
			</tbody>
		</table>
	</form>
	<br>
	<a href="add_user.jsp">Main menu</a>
	</center>
</body>
</html>