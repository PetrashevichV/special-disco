package by.news.service;

import by.news.service.impl.NewsServiceImpl;
import by.news.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private final UserService userService = new UserServiceImpl();
	private final NewsService newsService = new NewsServiceImpl();
	
	private ServiceProvider() {
		
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return userService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

}
