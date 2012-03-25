<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Subscription Events</title>
</head>
<body>
<h1>
	Subscription Event List
</h1>
Subscription: ${sub.id}<br/>
Things:
<c:forEach items="${sub.things}" var="thing">
	${thing}&nbsp;
</c:forEach><br/>
<h2>Events</h2>
<table>
<tr>
<td>id</td><td>clientTimestamp</td><td>serverTimestamp</td><td>data</td>
</tr>
<c:forEach items="${events}" var="event">
<tr>
	<td>${event.id}</td>
	<td>${event.clientTimestamp}</td>
	<td>${event.serverTimestamp}</td>
	<td>${event.data}</td>
</tr>
</c:forEach>
</table>
<a href="<c:url value='/subs'/>">Subscriptions</a>
<a href="<c:url value='/events'/>">Events</a>
<a href="<c:url value='/'/>">Home</a>
</body>
</html>
