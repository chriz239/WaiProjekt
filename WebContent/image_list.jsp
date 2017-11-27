<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<c:forEach var="image" items="$images">
		<tr>
			<td><image src="/bilder/thumbs/<c:out value=\"${image.uuid}\" />"</td>
		</tr>
	</c:forEach>



	<center>
			<td><a href="navigation_menu.jsp">Navigation menu</a></td>
	</center>
	
</body>
</html>