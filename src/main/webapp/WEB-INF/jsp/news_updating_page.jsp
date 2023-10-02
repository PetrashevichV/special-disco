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
<!-- Bootstrap CSS (jsDelivr CDN) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">
<!-- Bootstrap Bundle JS (jsDelivr CDN) -->
<script defer
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
	crossorigin="anonymous"></script>

<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
</head>

<body>

	<jsp:include page="header_log_in.jsp" />


	<div class="container">
		<form class="row g-3 needs-validation" novalidate
			action="frontController" method="post">

			<div class="mb-3"></div>
			<input type="hidden" name="command" value="update_news" /> <input
				type="hidden" name="news_id" value=${news.id } />
			<div class="mb-3">
				<label class="form-label">Title of news</label>
				<textarea name="title" class="form-control" rows="1"><c:out
						value="${news.title}" /></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">Brief of news</label>
				<textarea name="brief" class="form-control" rows="3"><c:out
						value="${news.brief}" /></textarea>
			</div>
			<div class="mb-3">
				<label class="form-label">Content of news</label>
				<textarea name="content" class="form-control" rows="18"><c:out
						value="${news.content}" /></textarea>
			</div>

			<div class="col-md-3 position-relative">
				<label for="validationCategory" class="form-label">Category
					of news</label> <select class="form-select" id="validationCategory"
					name="category" required>
					<option disabled value="">Choose...</option>
					<option value="0" selected>Political News</option>
					<option value="1">Entertainment News</option>
					<option value="2">Business News</option>
					<option value="3">Sports News</option>
				</select>
				<div class="invalid-tooltip">Please, choose correct category
					of news.</div>
			</div>

			<div class="mb-3">
				<br>
				<button class="btn btn-outline-dark">Update news</button>
			</div>
		</form>
	</div>



</body>
</html>