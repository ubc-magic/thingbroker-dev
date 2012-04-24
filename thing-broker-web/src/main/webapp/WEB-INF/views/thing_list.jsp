<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Things</title>
</head>
<body>

<h2>Things: ${thingName}</h2>
<ul>
<c:forEach var="thing" items="${things}">
<li><a href="<c:url value='/things/${thing.name}'/>">Thing: ${thing.name}</a></li>	
</c:forEach>
</ul>

<a href="<c:url value='/things?form'/>">Add Thing</a>
<a href="<c:url value='/'/>">Home</a>

</body>
</html>