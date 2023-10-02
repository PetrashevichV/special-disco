package by.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import by.news.bean.News;
import by.news.bean.NewsCategory;
import by.news.bean.NewsStatus;
import by.news.bean.User;
import by.news.dao.DaoExeption;
import by.news.dao.NewsDao;
import by.news.dao.connection_pool.ConnectionPool;
import by.news.dao.connection_pool.ConnectionPoolException;

public class NewsDaoImpl implements NewsDao {

	public static final String SELECT_NEWS = "SELECT n.*, u.u_login, ur.u_role_name "
			+ "FROM news n INNER JOIN user u ON u.u_id = n.n_author "
			+ "INNER JOIN user_role ur ON ur.u_role_id = u.u_role WHERE n_id = ?";

	public static final String SELECT_NEWS_FOR_PAGE = "SELECT n.*, u.u_login, ur.u_role_name FROM news n "
			+ "INNER JOIN user u ON u.u_id = n.n_author INNER JOIN user_role ur ON ur.u_role_id = u.u_role "
			+ "WHERE n_status = ? AND n_title LIKE ? ORDER BY n_date DESC LIMIT ?, ?";

	public static final String SELECT_COUNT_NEWS = "SELECT COUNT(*) as number FROM news WHERE n_status = ? AND n_title LIKE ?";

	public static final String INSERT_NEWS = "INSERT INTO news(n_category, n_title, n_brief, n_content, n_date, n_author, n_status) "
			+ "VALUES(?,?,?,?,?,?,?)";

	public static final String UPDATE_NEWS = "UPDATE news SET n_category = ?, n_title = ?, n_brief = ?, n_content = ?, "
			+ "n_author = ? WHERE n_id = ?";

	public static final String UPDATE_NEWS_STATUS_WITH_NEW_DATE = "UPDATE news SET n_status = ?, n_date = ?  WHERE n_id = ?";

	public static final String UPDATE_NEWS_STATUS_WITHOUT_NEW_DATE = "UPDATE news SET n_status = ?  WHERE n_id = ?";

	public NewsDaoImpl() {

	}

	@Override
	public void saveNews(News news) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_NEWS);
			ps.setInt(1, news.getCategory().ordinal());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getBrief());
			ps.setString(4, news.getContent());
			ps.setTimestamp(5, Timestamp.valueOf(news.getDate()));
			ps.setLong(6, news.getUser().getId());
			ps.setInt(7, news.getStatus().ordinal());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}
	}

	@Override
	public News getNews(int id) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		News news = null;
		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_NEWS);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				news = new News();
				news.setId(rs.getLong("n_id"));
				news.setTitle(rs.getString("n_title"));
				news.setBrief(rs.getString("n_brief"));
				news.setContent(rs.getString("n_content"));
				news.setDate(rs.getTimestamp("n_date").toLocalDateTime());
				news.setCategory(NewsCategory.fromValue(rs.getInt("n_category")));
				news.setStatus(NewsStatus.fromValue(rs.getInt("n_status")));
				User user = new User();
				user.setLogin(rs.getString("u_login"));
				user.setId(rs.getLong("n_author"));
				user.setRole(rs.getString("u_role_name"));
				news.setUser(user);
			}
		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}
		return news;
	}

	@Override
	public List<News> getListOfNews(int from, int numberOfNews, int status, String pattern) throws DaoExeption {
		List<News> listOfNews = new LinkedList<News>();
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_NEWS_FOR_PAGE);
			ps.setInt(1, status);
			ps.setString(2, "%" + pattern + "%");
			ps.setInt(3, from);
			ps.setInt(4, numberOfNews);

			rs = ps.executeQuery();

			while (rs.next()) {
				News news = new News();
				news.setId(rs.getLong("n_id"));
				news.setTitle(rs.getString("n_title"));
				news.setBrief(rs.getString("n_brief"));
				news.setContent(rs.getString("n_content"));
				news.setDate(rs.getTimestamp("n_date").toLocalDateTime());
				news.setCategory(NewsCategory.fromValue(rs.getInt("n_category")));
				User user = new User();
				user.setLogin(rs.getString("u_login"));
				user.setId(rs.getLong("n_author"));
				user.setRole(rs.getString("u_role_name"));
				news.setUser(user);
				listOfNews.add(news);
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}

		return listOfNews;
	}

	public long getNumberOfNews(int status, String pattern) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long numberOfNews = 0;
		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_COUNT_NEWS);
			ps.setInt(1, status);
			ps.setString(2, "%" + pattern + "%");

			rs = ps.executeQuery();

			rs.next();

			numberOfNews = rs.getLong("number");

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}
		return numberOfNews;
	}

	@Override
	public void updateNews(News news) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(UPDATE_NEWS);
			ps.setInt(1, news.getCategory().ordinal());
			ps.setString(2, news.getTitle());
			ps.setString(3, news.getBrief());
			ps.setString(4, news.getContent());
			ps.setLong(5, news.getUser().getId());
			ps.setLong(6, news.getId());
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}
	}

	@Override
	public void updateNewsStatusWithNewDate(int newsId, int newsStatus) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(UPDATE_NEWS_STATUS_WITH_NEW_DATE);
			ps.setInt(1, newsStatus);
			ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
			ps.setLong(3, newsId);
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}
	}

	@Override
	public void updateNewsStatusWithoutNewDate(int newsId, int newsStatus) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(UPDATE_NEWS_STATUS_WITHOUT_NEW_DATE);
			ps.setInt(1, newsStatus);
			ps.setLong(2, newsId);
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}
	}
}
