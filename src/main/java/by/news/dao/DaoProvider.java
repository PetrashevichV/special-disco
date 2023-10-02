package by.news.dao;

import by.news.dao.impl.NewsDaoImpl;
import by.news.dao.impl.UserDaoImpl;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final NewsDao newsDao = new NewsDaoImpl();
	private final UserDao userDao = new UserDaoImpl();

	private DaoProvider() {

	}

	public static DaoProvider getInstance() {
		return instance;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

}
