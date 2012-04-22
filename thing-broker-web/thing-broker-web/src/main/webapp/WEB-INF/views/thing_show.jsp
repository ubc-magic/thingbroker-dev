<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thing: ${thingName}</title>
</head>
<body>

<h2>Thing: ${thingName}</h2>
Information about an individual thing will go here.<br>
<a href="<c:url value='/things/${thingName}?form'/>">Edit Thing</a><br>
<a href="<c:url value='/things/${thingName}?_method=DELETE'/>">Delete Thing</a>
<a href="<c:url value='/'/>">Home</a>

</body>
</html>