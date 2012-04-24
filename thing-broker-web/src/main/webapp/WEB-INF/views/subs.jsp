<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Subscriptions</title>
</head>
<body>
<h1>
	Subscriptions test page
</h1>

List of existing subscriptions<br/>
<table>
<tr>
<td>id</td><td>things</td><td></td>
</tr>
<c:forEach items="${subs}" var="sub">
<tr>
	<td>${sub.id}</td>
	<td>
		<c:forEach items="${sub.things}" var="thing">
			${thing}&nbsp;
		</c:forEach>
	</td>
	<td>
		<a href="<c:url value='/subs/${sub.id}/events'/>">Get Events</a>&nbsp;<a href="#">Delete</a>
	</td>
</tr>
</c:forEach>
</table>
<h2>Create Subscription</h2>
<form method="post" action="<c:url value='/subs'/>">
	Thing: <input type="text" name="thing"/><br/>
	<input type="submit" value="Create"/>
</form>

<a href="<c:url value='/events'/>">Events</a>
<a href="<c:url value='/'/>">Home</a>

</body>
</html>
