<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />
<fmt:message bundle="${loc}" key="local.header.title" var="header_title" />
<fmt:message bundle="${loc}" key="local.button.en" var="button_en" />
<fmt:message bundle="${loc}" key="local.button.ru" var="button_ru" />
<fmt:message bundle="${loc}" key="local.button.sign.in"
	var="button_sign_in" />
<fmt:message bundle="${loc}" key="local.button.sign.up"
	var="button_sign_up" />

<div class="container">
	<header class="blog-header py-3">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-4 d-flex justify-content-start align-items-center">

				<form action="frontController" method="post">
					<input type="hidden" name="command" value="change_locale" /> <input
						type="hidden" name="local" value="ru" />
					<button class="btn btn-outline-dark">${button_ru}</button>
				</form>
				<div class="mx-3">
					<form action="frontController" method="post">
						<input type="hidden" name="command" value="change_locale" /> <input
							type="hidden" name="local" value="en" />
						<button class="btn btn-outline-dark">${button_en}</button>
					</form>
				</div>
			</div>
			<div class="col-4 text-center">
				<h2 class=" text-dark">
					<a
						href='<c:url value="/frontController?command=go_to_main_page" />'>${header_title}</a>
				</h2>
			</div>

			<div class="col-4 d-flex justify-content-end align-items-center">
				<div class="mx-3">
					<form action="frontController" method="post">
						<input type="hidden" name="command" value="authorization" />
						<button class="btn btn-outline-dark">${button_sign_in}</button>
					</form>
				</div>
				<form action="frontController" method="post">
					<input type="hidden" name="command" value="registration" />
					<button class="btn btn-outline-dark">${button_sign_up}</button>
				</form>
			</div>
		</div>
	</header>
</div>

