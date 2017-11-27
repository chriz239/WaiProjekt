<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <script>
  function getDate() {
	  for(var i = 0;i < document.getElementById("Date_from").length;i++)
	  {
	      if(document.getElementById("Date_from").value == "${Date_from}")
	      {
	          document.getElementById("Date_from").selectedIndex = i;
	      }
	  }
	  
	  for(var i = 0;i < document.getElementById("Date_till").length;i++)
	  {
	      if(document.getElementById("Date_till").value == "${Date_till}")
	      {
	          document.getElementById("Date_till").selectedIndex = i;
	      }
	  }
  }
  </script>

<title>Image Filter</title>
</head>
<body onload="getDate()">
<center>

	<h1>Search Webcam</h1>
	<form name="form_webcam_search" action="ImageFilter" method="post">
		<table border = "1">
		<tbody>
		
			<tr>
				<td>Cam wählen:
				<select name="camId">
					<c:forEach var="cam" items="${cams}" varStatus="i">
						<option name="camId" value="${cam.id}">${cam.name}</option>
					</c:forEach>
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
				<td><input type="datetime-local" name="Date_from" id="Date_from" value="${Date_from}"></td>
			</tr>
			<tr>
				<td><center>Bis</center></td>
			</tr>
			<tr>
				<td><input type="datetime-local" name="Date_till" id="Date_till" value="${Date_till}"></td>
			</tr>
			<tr>
				<td><center><input type="submit" name="btnSearch" value="Search"></center></td>
			</tr>
		</tbody>
		</table>
		</form>
		
		
	<br>
	<table border="1">
  		<tbody>
	  		<tr>	  						
				<td>Date:   </td>
				<td>Pic:     </td>	
			</tr>			
			<c:forEach var="pic" items="${images}" varStatus="i">
				<tr>
					<td>
					   <p><fmt:formatDate type = "both" value = "${pic.captureTime}" pattern="dd.MM.yyyy HH:mm" /></p>
					</td>					
					<!-- <td><a href="getPicID=${pics[i.index].id}"><img src="getPicID=${pics[i.index].id}&thumb" style="width:100px;height:100px;"></a> -->
					<td>
						<a href="${pic.imagePath}"><img src="${pic.thumbPath}" /></a>
					</td>
				</tr>
			</c:forEach>	
  		</tbody>
  	</table>

	<br>
		
		
		
		<p><a href="navigation_menu.jsp">Navigation menu</a></p>
		</center>

</body>
</html>