<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
.dropbtn {
    background-color: #0000FF;
    color: white;
    padding: 16px;
    font-size: 16px;
    border: none;
    cursor: pointer;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content 
{
    display: none;
    position: absolute;
    right: 0;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a 
{
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #0000FF}

.dropdown:hover .dropdown-content 
{
    display: block;
}

.dropdown:hover .dropbtn 
{
    background-color: #0000FF;
}
</style>

<title>navigation menu</title>
</head>
<body>
<center>
	<h1>Navigation Menu</h1>
	<!-- 
 	<div class="dropdown">
  	<button class="dropbtn"><h3>User</h3> Username: </button>
  		<div class="dropdown-content">
    		<a href="image_filter.jsp">Search webcam</a>
  		</div>
	</div>
 -->
 	<div class="dropdown">
  	<button class="dropbtn"><h3>Admin</h3></button>
  		<div class="dropdown-content">
    		<a href="AddUser">Add user</a>
    		<a href="AddCam">Add webcam</a>
    		<a href="UserList">User list</a>
    		<!-- <a href="webcam_list.jsp">Webcam list</a> -->
 		 </div>
	</div>
	
<p>
<form name="logout" action="logout_mod" method="post">
	<input type="submit" name="btnLogout" value="Logout">
</form>
	
</center>
</body>
</html>