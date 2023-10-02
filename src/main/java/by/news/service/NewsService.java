package by.news.service;

import java.util.List;

import by.news.bean.News;
import by.news.dao.DaoExeption;

public interface NewsService {

	public void addNews(News news) throws ServiceExeption;

	public void updateNews(News news) throws ServiceExeption;

	public void updateNewsStatusWithNewDate(int newsId, int newsStatus) throws ServiceExeption;

	public void updateNewsStatusWithoutNewDate(int newsId, int newsStatus) throws ServiceExeption;

	public List<News> getListOfNews(int from, int numberOfNews, int status, String pattern) throws ServiceExeption;

	public News getNews(int id) throws ServiceExeption;

	public long getNumberOfNews(int status, String pattern) throws ServiceExeption;

}
