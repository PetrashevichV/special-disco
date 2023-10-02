package by.news.dao;

import java.util.List;

import by.news.bean.News;

public interface NewsDao {

	public void saveNews(News news) throws DaoExeption;

	public List<News> getListOfNews(int from, int numberOfNews, int status, String pattern) throws DaoExeption;

	public News getNews(int id) throws DaoExeption;

	public long getNumberOfNews(int status, String pattern) throws DaoExeption;

	public void updateNews(News news) throws DaoExeption;

	public void updateNewsStatusWithNewDate(int newsId, int newsStatus) throws DaoExeption;

	public void updateNewsStatusWithoutNewDate(int newsId, int newsStatus) throws DaoExeption;

}
