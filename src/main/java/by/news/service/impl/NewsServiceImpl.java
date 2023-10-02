package by.news.service.impl;

import java.util.List;

import by.news.bean.News;
import by.news.dao.DaoExeption;
import by.news.dao.DaoProvider;
import by.news.dao.NewsDao;

import by.news.service.NewsService;
import by.news.service.ServiceExeption;

public class NewsServiceImpl implements NewsService {

	private static final DaoProvider provider = DaoProvider.getInstance();
	private static final NewsDao newsDao = provider.getNewsDao();

	@Override
	public void addNews(News news) throws ServiceExeption {
		try {
			newsDao.saveNews(news);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	@Override
	public void updateNews(News news) throws ServiceExeption {
		try {
			newsDao.updateNews(news);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	public void updateNewsStatusWithNewDate(int newsId, int newsStatus) throws ServiceExeption {
		try {
			newsDao.updateNewsStatusWithNewDate(newsId, newsStatus);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	public void updateNewsStatusWithoutNewDate(int newsId, int newsStatus) throws ServiceExeption {
		try {
			newsDao.updateNewsStatusWithoutNewDate(newsId, newsStatus);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
	}

	public News getNews(int id) throws ServiceExeption {
		News news = null;
		try {
			news = newsDao.getNews(id);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
		return news;
	}

	@Override
	public List<News> getListOfNews(int from, int numberOfNews, int status, String pattern) throws ServiceExeption {
		List<News> list = null;
		try {
			list = newsDao.getListOfNews(from, numberOfNews, status, pattern);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
		return list;
	}

	public long getNumberOfNews(int status, String pattern) throws ServiceExeption {
		long numberOfNews = 0;
		try {
			numberOfNews = newsDao.getNumberOfNews(status, pattern);
		} catch (DaoExeption e) {
			// TODO log
			throw new ServiceExeption(e);
		}
		return numberOfNews;

	}

}
