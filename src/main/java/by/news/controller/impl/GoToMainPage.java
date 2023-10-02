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

public class GoToMainPage implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final NewsService newsService = provider.getNewsService();

	private static final String PATH = "/WEB-INF/jsp/main.jsp";
	private static final String URL = "frontController?command=go_to_main_page";
	private static final String ATTRIBUTE_NAME_URL = "url";
	private static final String REQUEST_ATTRIBUTE_LIST_NEWS = "list_of_news";
	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";

	private static final int FIRST_NEWS = 0;
	private static final String INITIAL_NEWS_PATTERN = "";

	private static final int NEWS_STATUS_APPROVED = NewsStatus.APPROVED.ordinal();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int limitOnPage = Integer.parseInt(Config.getProperty(Config.DB_LIMIT));

		List<News> listOfNews = new LinkedList<>();
		try {
			listOfNews = newsService.getListOfNews(FIRST_NEWS, limitOnPage, NEWS_STATUS_APPROVED, INITIAL_NEWS_PATTERN);
		} catch (ServiceExeption e) {
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
			return;
			// TODO eror.jsp, log
		}

		request.setAttribute(REQUEST_ATTRIBUTE_LIST_NEWS, listOfNews);

		request.getSession().setAttribute(ATTRIBUTE_NAME_URL, URL);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
		requestDispatcher.forward(request, response);
	}

}
