<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Add Player</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />

<meta http-equiv="Content-type" content="text/html; charset=ISO-8859-1">
<title>Add players to ${team}</title>
</head>
<body>
	<div class="col-md-4">
		<p>${errorMessage}</p>>
		<form:form id="addPlayerForm" method="post" action="addplayer" modelAttribute="teamPlayer">
			<form:label path="team">Select Team: </form:label>
			<form:select id="team" name="team" path="team" items="${footballTeams}" />
			<br>
			<form:label path="playerName">Add Player: </form:label>
			<form:input id="playerName" name="playerName" path="playerName" />
			<br>
			<input type="submit" value="Submit" />
		</form:form>
	</div>

</body>
</html>