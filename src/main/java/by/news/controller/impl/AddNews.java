package by.news.controller.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import by.news.bean.News;
import by.news.bean.NewsCategory;
import by.news.bean.NewsStatus;
import by.news.bean.User;
import by.news.controller.Command;
import by.news.service.NewsService;
import by.news.service.ServiceExeption;
import by.news.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AddNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";
	private static final String REDIRECT_GO_TO_AUTH_USER_PAGE = "frontController?command=go_to_auth_user_page";
	private static final String REDIRECT_AUTHORIZATION = "frontController?command=authorization";

	private static final String REQUEST_PARAMETR_TITLE = "title";
	private static final String REQUEST_PARAMETR_BRIEF = "brief";
	private static final String REQUEST_PARAMETR_CONTENT = "content";
	private static final String REQUEST_PARAMETR_CATEGORY = "category";
	private static final String REQUEST_ATTRIBUTE_USER = "user";
	private static final String USER_ROLE_ADMIN = "admin";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(REDIRECT_AUTHORIZATION);
			return;
		}

		User user = (User) session.getAttribute(REQUEST_ATTRIBUTE_USER);

		if (user == null) {
			response.sendRedirect(REDIRECT_AUTHORIZATION);
			return;
		}

		String title = request.getParameter(REQUEST_PARAMETR_TITLE);
		String brief = request.getParameter(REQUEST_PARAMETR_BRIEF);
		String content = request.getParameter(REQUEST_PARAMETR_CONTENT);
		int category = Integer.parseInt(request.getParameter(REQUEST_PARAMETR_CATEGORY));

		News news = new News();
		news.setCategory(NewsCategory.fromValue(category));
		news.setTitle(title);
		news.setBrief(brief);
		news.setContent(content);
		news.setDate(LocalDateTime.now());
		if (USER_ROLE_ADMIN.equals(user.getRole())) {
			news.setStatus(NewsStatus.APPROVED);
		} else {
			news.setStatus(NewsStatus.PROPOSED);
		}
		news.setUser(user);

		try {
			newsService.addNews(news);
			response.sendRedirect(REDIRECT_GO_TO_AUTH_USER_PAGE);
		} catch (ServiceExeption e) {
			// log + eror.jsp
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
		}
	}

}
