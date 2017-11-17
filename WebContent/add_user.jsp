<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>Edit User</title>
  </head>  
  <body>
  	<center>
  	<h1>Add User</h1>
	<form name="form_user_edit" action="user_mod" method="post">		
		<table border="1">
			<tbody>
				
				<tr>
					<td>Username:</td>
					<td><input type="text" name="username" value=""></td>		
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="text" name="password" value=""></td>		
				</tr>
				<tr>	
					<td>First Name:</td>
					<td><input type="text" name="firstname" value=""></td>		
				</tr>
				<tr>		
					<td>Last Name:</td>	
					<td><input type="text" name="lastname" value=""></td>
				</tr>		
				<tr>		
					<td>user_cam:</td>	
					<td><input type="text" name="user_cam" value=""></td>
				</tr>
				<tr>		
					<td>user_mode:</td>	
					<td><input type="text" name="user_mode" value=""></td>
				</tr>
				

				<tr>	
					<td colspan="2"><center><input type="submit" name="btnAdd" value="Add"></center></td>
				</tr>				
			</tbody>
		</table>
	</form>
	<a href="main.jsp">Main menu</a>
	</center>
  </body>
</html>
