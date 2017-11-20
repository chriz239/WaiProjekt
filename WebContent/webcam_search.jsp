<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Webcam</title>
</head>
<body>
<center>

	<h1>Search Webcam</h1>
	<form name="form_webcam_search" action="view_webcam_search" method="post">
		<table border = "1">
		<tbody>
		
			<tr>
				<td>Cam wählen:
				<select>
					<option value="${camId}">cam1</option>
					<option value="${camId}">cam2</option>
					<option value="${camId}">cam3</option>
					<option value="${camId}">cam4</option>
				</select>
				</td>
			</tr>
			<tr>
				<td><center>Zeitraum:</center></td>
			</tr>
			<tr>
				<td><center>Von</center></td>
			</tr>
			<tr>
				<td><input type="datetime-local" name="Date_from" value=""></td>
			</tr>
			<tr>
				<td><center>Bis</center></td>
			</tr>
			<tr>
				<td><input type="datetime-local" name="Date_till" value=""></td>
			</tr>
			<tr>
				<td><center><input type="submit" action="btnSearch" value="Search"></center></td>
			</tr>
		</tbody>
		</table>
		</form>
		
		<p><a href="main.jsp">Main menu</a></p>
		</center>

</body>
</html>