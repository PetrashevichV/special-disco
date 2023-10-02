package by.news.controller.impl;

import java.io.IOException;

import by.news.bean.News;
import by.news.bean.User;
import by.news.controller.Command;
import by.news.service.NewsService;
import by.news.service.ServiceExeption;
import by.news.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReadNews implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final String REQUEST_ATTRIBUTE_NEWS = "news";
	private static final String SESSION_ATTRIBUTE_USER = "user";
	private static final String REQUEST_PARAMETR_NEWS_ID = "news_id";
	private static final String PATH = "/WEB-INF/jsp/news_page.jsp";

	private static final String URL = "frontController?command=read_news&news_id=";
	private static final String ATTRIBUTE_NAME_URL = "url";
	private static final String REDIRECT_AUTHORIZATION_ERROR_PATH = "frontController?command=authorization&message_error=Please sign in";
	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(SESSION_ATTRIBUTE_USER);

		if (user == null) {
			response.sendRedirect(REDIRECT_AUTHORIZATION_ERROR_PATH);
		} else {

			News news = null;
			int newsId = Integer.parseInt(request.getParameter(REQUEST_PARAMETR_NEWS_ID));
			try {
				news = newsService.getNews(newsId);
			} catch (ServiceExeption e) {
				response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
				return;
				// TODO eror.jsp, log
			}

			request.setAttribute(REQUEST_ATTRIBUTE_NEWS, news);

			session.setAttribute(ATTRIBUTE_NAME_URL, URL + newsId);

			RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
			requestDispatcher.forward(request, response);

		}

	}

}