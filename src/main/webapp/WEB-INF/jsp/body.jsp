<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<fmt:message bundle="${loc}" key="local.button.delete.news"
	var="button_delete_news" />
<fmt:message bundle="${loc}" key="local.button.update.news"
	var="button_update_news" />
<fmt:message bundle="${loc}" key="local.tabs.main.news"
	var="tabs_main_news" />
<fmt:message bundle="${loc}" key="local.tabs.proposed.news"
	var="tabs_proposed_news" />
<fmt:message bundle="${loc}" key="local.tabs.deleted.news"
	var="tabs_deleted_news" />
<fmt:message bundle="${loc}" key="local.tabs.update.news"
	var="tabs_update_news" />
<fmt:message bundle="${loc}" key="local.button.search"
	var="button_search" />
<fmt:message bundle="${loc}" key="local.text.search" var="text_search" />
<fmt:message bundle="${loc}" key="local.button.next" var="button_next" />
<fmt:message bundle="${loc}" key="local.button.previous"
	var="button_previous" />


<div class="container">

	<div class="mt-3">
		<c:if test="${user.role == 'admin'}">

			<c:if test="${url == 'frontController?command=go_to_auth_user_page'}">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="frontController?command=go_to_auth_user_page">${tabs_main_news}</a></li>
					<li class="nav-item"><a class="nav-link"
						href="frontController?command=go_to_proposed_news_page">${tabs_proposed_news}</a></li>
					<li class="nav-item"><a class="nav-link"
						href="frontController?command=go_to_deleted_news_page">${tabs_deleted_news}</a>
				</ul>
			</c:if>


			<c:if
				test="${url == 'frontController?command=go_to_proposed_news_page'}">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="frontController?command=go_to_auth_user_page">${tabs_main_news}</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="frontController?command=go_to_proposed_news_page">${tabs_proposed_news}</a></li>
					<li class="nav-item"><a class="nav-link"
						href="frontController?command=go_to_deleted_news_page">${tabs_deleted_news}</a>
				</ul>
			</c:if>


			<c:if
				test="${url == 'frontController?command=go_to_deleted_news_page'}">
				<ul class="nav nav-pills">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="frontController?command=go_to_auth_user_page">${tabs_main_news}</a></li>
					<li class="nav-item"><a class="nav-link"
						href="frontController?command=go_to_proposed_news_page">${tabs_proposed_news}</a></li>
					<li class="nav-item"><a class="nav-link active"
						href="frontController?command=go_to_deleted_news_page">${tabs_deleted_news}</a>
				</ul>
			</c:if>


		</c:if>
	</div>



	<div>
		<c:if test="${user != null}">
			<br>
			<form class="d-flex" action="frontController" method="post">

				<input class="form-control me-2" name="pattern_news" type="search"
					placeholder="${text_search}" aria-label="Search" id="pattern_news">

				<a class="btn btn-outline-dark" href=''
					onclick="this.href='${url}&pattern_news='+document.getElementById('pattern_news').value">${button_search}</a>

			</form>
			<br>
		</c:if>
	</div>

	<div>
		<c:if test="${user.role == 'admin'}">
			<form action="frontController" method="post">
				<input type="hidden" name="command" value="go_to_adding_news_page" />
				<button class="btn btn-outline-dark">${button_add_news}</button>
			</form>
		</c:if>


		<c:if test="${user.role == 'user'}">
			<form action="frontController" method="post">
				<input type="hidden" name="command" value="go_to_adding_news_page" />
				<button class="btn btn-outline-dark">${button_propose_news}</button>
			</form>
		</c:if>
	</div>

	<br>


	<c:if test="${list_of_news.size()==0}">
		<p align="center">News not found</p>
	</c:if>
	<c:forEach var="news" items="${list_of_news}">
		<div
			class="row no-gutters border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative">
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
					<fmt:formatDate pattern="dd.MM.yyyy HH:mm"
						value="${parsedDateTime}" />
				</div>
				<p class="card-text mb-auto">
					<c:out value="${news.brief}" />
				</p>

				<a href="frontController?command=read_news&news_id=${news.id}"
					class="stretched-link">Continue reading</a>

			</div>
		</div>

		<c:if test="${url == 'frontController?command=go_to_auth_user_page'}">
			<c:if test="${user.role == 'admin'}">
				<div class="mt-3">
					<div class="d-flex justify-content-start align-items-center">

						<form action="frontController" method="post">
							<input type="hidden" name="command"
								value="go_to_updating_news_page" /> <input type="hidden"
								name="news_id" value=${news.id } />
							<button class="btn btn-outline-dark">${button_update_news}</button>
						</form>

						<div class="mx-3">
							<form action="frontController" method="post">
								<input type="hidden" name="command" value="delete_news" /> <input
									type="hidden" name="news_id" value=${news.id } />
								<button class="btn btn-outline-dark">${button_delete_news}</button>
							</form>
						</div>
					</div>
				</div>
			</c:if>
		</c:if>

		<br>
		<br>
	</c:forEach>



	<c:if test="${user != null}">

		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<c:if test="${current_page != 1}">
					<li class="page-item"><a class="page-link"
						href="${url}&page=${current_page - 1}">${button_previous}</a></li>
				</c:if>

				<c:forEach begin="1" end="${number_of_pages}" var="i">
					<c:choose>
						<c:when test="${current_page == i}">
							<li class="page-item active" aria-current="page"><span
								class="page-link">${i}</span></li>
						</c:when>
						<c:otherwise>
							<li class="page-item"><a class="page-link"
								href="${url}&page=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>

				<c:if test="${current_page < number_of_pages}">
					<li class="page-item"><a class="page-link"
						href="${url}&page=${current_page + 1}">${button_next}</a></li>
				</c:if>

			</ul>
		</nav>

	</c:if>
</div>