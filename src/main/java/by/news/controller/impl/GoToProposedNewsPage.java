package by.news.controller.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import by.news.bean.News;
import by.news.bean.NewsStatus;
import by.news.controller.Command;
import by.news.dao.conection_pool.config.Config;
import by.news.service.NewsService;
import by.news.service.ServiceExeption;
import by.news.service.ServiceProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToProposedNewsPage implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final String PATH = "/WEB-INF/jsp/auth_user_page.jsp";
	private static final String URL = "frontController?command=go_to_proposed_news_page";
	private static final String ATTRIBUTE_NAME_URL = "url";
	private static final String REQUEST_ATTRIBUTE_LIST_NEWS = "list_of_news";
	private static final String REQUEST_ATTRIBUTE_NUMBER_OF_PAGE = "number_of_pages";
	private static final String REQUEST_ATTRIBUTE_CURRENT_PAGE = "current_page";
	private static final String REQUEST_PARAMETER_PAGE = "page";
	private static final String REQUEST_PARAMETER_PATTERN_NEWS = "pattern_news";
	private static final String INITIAL_NEWS_PATTERN = "";
	private static final int INITIAL_PAGE = 1;
	private static final int INITIAL_NUMBER_OF_PAGES = 0;

	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";
	private static final String REDIRECT_AUTHORITHATION = "frontController?command=authorization";

	private static final int NEWS_STATUS_PROPOSED = NewsStatus.PROPOSED.ordinal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(REDIRECT_AUTHORITHATION);
			return;
		}

		int page = INITIAL_PAGE;
		int limitOnPage = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));
		if (request.getParameter(REQUEST_PARAMETER_PAGE) != null)
			page = Integer.parseInt(request.getParameter(REQUEST_PARAMETER_PAGE));
		List<News> listOfNews = new LinkedList<>();
		int numberOfPages = INITIAL_NUMBER_OF_PAGES;

		String newsPattern = INITIAL_NEWS_PATTERN;
		if (request.getParameter(REQUEST_PARAMETER_PATTERN_NEWS) != null)
			newsPattern = request.getParameter(REQUEST_PARAMETER_PATTERN_NEWS);

		try {
			long numberOfNews = newsService.getNumberOfNews(NEWS_STATUS_PROPOSED, newsPattern);
			numberOfPages = (int) Math.ceil(numberOfNews * 1.0 / limitOnPage);
			listOfNews = newsService.getListOfNews((page - 1) * limitOnPage, limitOnPage, NEWS_STATUS_PROPOSED,
					newsPattern);
		} catch (ServiceExeption e) {
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
			return;
			// TODO eror.jsp, log
		}

		request.setAttribute(REQUEST_ATTRIBUTE_LIST_NEWS, listOfNews);
		request.setAttribute(REQUEST_ATTRIBUTE_NUMBER_OF_PAGE, numberOfPages);
		request.setAttribute(REQUEST_ATTRIBUTE_CURRENT_PAGE, page);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
		request.getSession().setAttribute(ATTRIBUTE_NAME_URL, URL);

		requestDispatcher.forward(request, response);
	}

}
