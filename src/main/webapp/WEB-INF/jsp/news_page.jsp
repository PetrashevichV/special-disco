<%@page import="by.news.bean.News"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.button.publish.news"
	var="button_publish_news" />
<fmt:message bundle="${loc}" key="local.button.restore.news"
	var="button_restore_news" />
</head>

<body>

	<jsp:include page="header_log_in.jsp" />
	<br />

	<div class="col p-4 d-flex flex-column position-static">
		<strong class="d-inline-block mb-2 text-primary"> <c:out
				value="${news.category.name}" />
		</strong>
		<div class="mb-1 text-muted">
			<c:out value="Author - ${news.user.login}" />
		</div>
		<h3 class="mb-0">
			<c:out value="${news.title}" />
		</h3>
		<div class="mb-1 text-muted">
			<fmt:parseDate value="${news.date}" pattern="yyyy-MM-dd'T'HH:mm"
				var="parsedDateTime" type="both" />
			<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
		</div>

		<div class="mb-3">
			<p class="card-text mb-auto" style="white-space: pre-line">
				<c:out value="${news.content}" />
			</p>
		</div>


		<c:if test="${user.role == 'admin'}">
			<c:if test="${news.status.ordinal() == 1}">
				<form action="frontController" method="post">
					<input type="hidden" name="command" value="approve_proposed_news" />
					<input type="hidden" name="news_id" value=${news.id } />
					<button class="btn btn-outline-dark">${button_publish_news}</button>
				</form>
			</c:if>
		</c:if>

		<c:if test="${user.role == 'admin'}">
			<c:if test="${news.status.ordinal() == 2}">
				<form action="frontController" method="post">
					<input type="hidden" name="command" value="restore_deleted_news" />
					<input type="hidden" name="news_id" value=${news.id } />
					<button class="btn btn-outline-dark">${button_restore_news}</button>
				</form>
			</c:if>
		</c:if>


	</div>


	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>