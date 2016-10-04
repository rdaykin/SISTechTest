<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Football Team Homepage</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Football Team Webservice Homepage</a>
		</div>
	</div>
</nav>

<div class="container">

	<div class="row">
		<div class="col-md-4">
			<h2>Create a Team</h2>
			<p>Set the details for a new team, including name, owner, city and competition</p>
			<p>
				<a class="btn btn-default" href="newteam" role="button">GO!</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Add Players</h2>
			<p>Add players to teams you have created</p>
			<p>
				<a class="btn btn-default" href="addplayer" role="button">GO!</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>View Teams</h2>
			<p>View the names of all teams</p>
			<p>
				<a class="btn btn-default" href="teamnames" role="button">GO!</a>
			</p>
			<p>
				<a class="btn btn-default" href="teamdetails/all" role="button">GO!</a>
			</p>
		</div>
	</div>


	<hr>
	<footer>
		<p>&copy; Rob Daykin 2016</p>
	</footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js" var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>