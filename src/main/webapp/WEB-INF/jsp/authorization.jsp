<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="resources/css/signing.css">
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.authorization.title"
	var="authorization_title" />
<fmt:message bundle="${loc}" key="local.form.login" var="login" />
<fmt:message bundle="${loc}" key="local.form.password" var="password" />

<fmt:message bundle="${loc}" key="local.button.sign.in"
	var="button_sign_in" />

</head>


<body class="text-center">

	<jsp:include page="header_main.jsp" />

	<font color="green" size="4"> <c:out value="${param.message}" />
	</font>


	<font color="red" size="4"> <c:out
			value="${param.message_error}" />
	</font>

	<form class="form-signin" action="frontController" method="post">

		<h4 class="h4 mb-3 font-weight-normal">${authorization_title}</h4>
		<input type="hidden" name="command" value="sign_in" />
		<div class="mb-3">
			<input name="login" id="inputEmail" class="form-control"
				placeholder="${login}" required autofocus>
		</div>
		<div class="mb-3">
			<input name="password" id="inputPassword" class="form-control"
				placeholder="${password}" required>
		</div>
		<button class="btn btn-outline-dark">${button_sign_in}</button>
		<p class="mt-5 mb-3 text-muted">&copy; 2021-2021</p>
	</form>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>