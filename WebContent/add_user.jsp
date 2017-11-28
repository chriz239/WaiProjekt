<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Add User</title>
  </head>  
  <body>
  	<center>
  	<h1>Add User</h1>
	<form name="add_user" action="AddUser" method="post">		
		<table border="0">
			<tbody>
				
				<tr>
					<td><center>Username</center></td>	
				</tr>
				<tr>
					<td><input type="text" name="name" value=""></td>	
				</tr>
				<tr>
					<td><center>Password</center></td>		
				</tr>
				<tr>
					<td><input type="password" name="password" value=""></td>
				</tr>
				<tr>	
					<td colspan="2"><center><input type="submit"></center></td>
				</tr>				
			</tbody>
		</table>
	</form>
	<p><a href="navigation_menu.jsp">Navigation menu</a></p>
	</center>
  </body>
</html>
