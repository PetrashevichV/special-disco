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
<link rel="stylesheet" type="text/css" href="resources/css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.registration.title"
	var="registration_title" />
<fmt:message bundle="${loc}" key="local.form.login" var="login" />
<fmt:message bundle="${loc}" key="local.form.email" var="email" />
<fmt:message bundle="${loc}" key="local.form.password" var="password" />
<fmt:message bundle="${loc}" key="local.form.confirm.password"
	var="confirm_password" />
<fmt:message bundle="${loc}" key="local.button.sign.up"
	var="button_sign_up" />

</head>

<body class="text-center">

	<jsp:include page="header_main.jsp" />

	<font color="red" size="4"> <c:out
			value="${param.message_error}" />
	</font>

	<form class="form-signin" action="frontController" method="post">


		<h4 class="h4 mb-3 font-weight-normal">${registration_title}</h4>
		<input type="hidden" name="command" value="registration_new_user" />
		<div class="mb-3">
			<input name="login" id="inputLogin" class="form-control"
				placeholder="${login }" required autofocus>
		</div>
		<div class="mb-3">
			<input  name="email" id="inputEmail" class="form-control"
				placeholder="${email }" required>
		</div>
		<div class="mb-3">
			<input  name="password" id="inputPassword"
				class="form-control" placeholder="${password }" required>
		</div>
		<div class="mb-3">
			<input  name="confirmPassword"
				id="inputConfirmPassword" class="form-control"
				placeholder="${confirm_password}" required>
		</div>

		<button class="btn btn-outline-dark">${button_sign_up}</button>

	</form>

	<!-- JavaScript Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
		crossorigin="anonymous"></script>
</body>
</html>