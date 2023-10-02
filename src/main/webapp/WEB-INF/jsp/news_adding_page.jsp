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

<fmt:message bundle="${loc}" key="local.button.add.news"
	var="button_add_news" />
<fmt:message bundle="${loc}" key="local.button.propose.news"
	var="button_propose_news" />
<fmt:message bundle="${loc}" key="local.text.choose" var="text_choose" />
<fmt:message bundle="${loc}" key="local.heading.news.title"
	var="heading_news_title" />
<fmt:message bundle="${loc}" key="local.heading.news.brief"
	var="heading_news_brief" />
<fmt:message bundle="${loc}" key="local.heading.news.content"
	var="heading_news_content" />
<fmt:message bundle="${loc}" key="local.heading.news.category"
	var="heading_news_category" />

<fmt:message bundle="${loc}" key="local.political.news.category"
	var="political_news_category" />
<fmt:message bundle="${loc}" key="local.entertainment.news.category"
	var="entertainment_news_category" />
<fmt:message bundle="${loc}" key="local.business.news.category"
	var="business_news_category" />
<fmt:message bundle="${loc}" key="local.sports.news.category"
	var="sports_news_category" />

</head>

<body>

	<jsp:include page="header_log_in.jsp" />


	<div class="container">
		<br>
		<form class="row g-3 needs-validation" novalidate
			action="frontController" method="post">

			<input type="hidden" name="command" value="add_news" />
			<div class="mb-3">
				<label class="form-label">${heading_news_title}</label>
				<textarea name="title" class="form-control" rows="1"></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">${heading_news_brief}</label>
				<textarea name="brief" class="form-control" rows="3"></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">${heading_news_content}</label>
				<textarea name="content" class="form-control" rows="18"></textarea>
			</div>



			<div class="col-md-3 position-relative">
				<label for="validationCategory" class="form-label">${heading_news_category}</label>
				<select class="form-select" id="validationCategory" name="category"
					required>
					<option disabled value="">${text_choose}</option>
					<option value="0" selected>${political_news_category}</option>
					<option value="1">${entertainment_news_category}</option>
					<option value="2">${business_news_category}</option>
					<option value="3">${sports_news_category}</option>
				</select>
				<div class="invalid-tooltip">Please, choose correct category
					of news.</div>
			</div>
			<br>

			<c:if test="${user.role == 'admin'}">
				<div class="mb-3">
					<br>
					<button class="btn btn-outline-dark">${button_add_news}</button>
				</div>
			</c:if>

			<c:if test="${user.role == 'user'}">
				<div class="mb-3">
					<br>
					<button class="btn btn-outline-dark">${button_propose_news}</button>
				</div>
			</c:if>
		</form>
	</div>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>