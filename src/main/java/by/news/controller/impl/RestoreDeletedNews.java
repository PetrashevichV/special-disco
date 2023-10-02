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

public class RestoreDeletedNews implements Command {

	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";
	private static final String REDIRECT_AUTHORITHATION = "frontController?command=authorization";
	private static final String REDIRECT_GO_TO_DELETED_NEWS_PAGE = "frontController?command=go_to_deleted_news_page";

	private static final String REQUEST_PARAMETR_NEWS_ID = "news_id";
	private static final String SESSION_ATTRIBUTE_USER = "user";

	private static final int NEWS_STATUS_APPROVED = NewsStatus.APPROVED.ordinal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(REDIRECT_AUTHORITHATION);
			return;
		}

		User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

		if (user == null) {
			response.sendRedirect(REDIRECT_AUTHORITHATION);
			return;
		}

		int newsId = Integer.parseInt(request.getParameter(REQUEST_PARAMETR_NEWS_ID));

		try {

			newsService.updateNewsStatusWithoutNewDate(newsId, NEWS_STATUS_APPROVED);

			response.sendRedirect(REDIRECT_GO_TO_DELETED_NEWS_PAGE);
		} catch (ServiceExeption e) {
			// log + eror.jsp
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
		}
	}

}
