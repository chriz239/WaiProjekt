<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="servlets.Login" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>
<center><h1>Login</h1></center>

	<form name="Login" action="login" method="post">
		<center>
		<table border="0">
			<tbody>
				<tr>
					<td>Username:</td>
					<td><input type="text" name="name" value=""></td>		
				</tr>
				<tr>		
					<td>Password:</td>	
					<td><input type="password" name="password" value=""></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="radio" id="UserMode" name="selectMode" value="uMode">
						<label for="UserMode">User Mode</label>
						<input type="radio" id="PrivilegeMode" name="selectMode" value="pMode">
						<label for="PrivilegeMode">Privilege Mode</label>
					</td>
				</tr>
				<tr>	
					<td colspan="2"><center><input type="submit" name="btnLogin" value="login"></center></td>
				</tr>
			</tbody>
		</table>
		</center>
	</form>
</body>
</html>