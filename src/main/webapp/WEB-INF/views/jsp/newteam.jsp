<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Create New Team</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<title>New Team</title>
</head>
<body>
	<div class="col-md-4">
		<p>${errorMessage}</p>>
		<form:form id="newTeamForm" method="post" action="newteam" modelAttribute="footballTeam">
			<form:label path="name">Name</form:label>
			<form:input id="name" name="name" path="name" />
			<br>
			<form:label path="city">City</form:label>
			<form:input id="city" name="city" path="city" />
			<br>
			<form:label path="owner">Owner</form:label>
			<form:input id="owner" name="owner" path="owner" />
			<br>
			<form:label path="competition">Competition</form:label>
			<form:input id="competition" name="competition" path="competition" />
			<br>
			<input type="submit" value="Submit" />
		</form:form>
	</div>

</body>
</html>