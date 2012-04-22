<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Thing Broker</title>
</head>
<body>
<h1>
	Welcome to Thing Broker!
</h1>

<P> This is the thing broker home page</P>
<P> The time on the server is ${serverTime}. </P>

<ul>
<li><a href="<c:url value='/things'/>">Things Page</a></li>
<li><a href="<c:url value='/events'/>">Events Test Page</a></li>
<li><a href="<c:url value='/subs'/>">Subscriptions Test Page</a></li>
</ul>

</body>
</html>
