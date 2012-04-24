<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Events</title>
</head>
<body>
<h1>
	Send events to ${thingName}
</h1>

<form action="<c:url value='/api/v1/events/${thingName}'/>" method="post">
	<input type="text" name="value"/>
	<input type="submit">
</form>

<a href="<c:url value='/subs'/>">Subscriptions</a>
<a href="<c:url value='/'/>">Home</a>

</body>
</html>
