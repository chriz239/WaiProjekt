<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="models.*" %>
<%@ page import="servlets.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Edit User</title>
  </head>  
  <body>
  	<center>
  	<h1>Add User</h1>
	<form name="add_user" action="user_mod" method="post">		
		<table border="0">
			<tbody>
				
				<tr>
					<td><center>Username</center></td>	
				</tr>
				<tr>
					<td><input type="text" name="username" value=""></td>	
				</tr>
				<tr>
					<td><center>Password</center></td>		
				</tr>
				<tr>
					<td><input type="text" name="password" value=""></td>
				</tr>
				<tr>	
					<td><center>First Name</center></td>		
				</tr>
				<tr>
					<td><input type="text" name="firstname" value=""></td>
				</tr>
				<tr>		
					<td><center>Last Name</center></td>	
				</tr>
				<tr>
					<td><input type="text" name="lastname" value=""></td>
				</tr>		
				<tr>		
					<td><center>user_cam</center></td>	
				</tr>
				<tr>
					<td><input type="text" name="user_cam" value=""></td>
				</tr>
				<tr>		
					<td><center>user_mode</center></td>	
				</tr>
				<tr>
					<td><input type="text" name="user_mode" value=""></td>
				</tr>

				<tr>	
					<td colspan="2"><center><input type="submit" name="btnAdd" value="Add"></center></td>
				</tr>				
			</tbody>
		</table>
	</form>
	<p><a href="main.jsp">Main menu</a></p>
	</center>
  </body>
</html>
