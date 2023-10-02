package by.news.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;
import by.news.dao.DaoExeption;
import by.news.dao.UserDao;
import by.news.dao.connection_pool.ConnectionPool;
import by.news.dao.connection_pool.ConnectionPoolException;

public class UserDaoImpl implements UserDao {
	private static final int USER_ROLE_ID = 0;

	public static final String INSERT_USER = "INSERT INTO user(u_login, u_password, u_email, u_role) VALUES(?,?,?,?)";

	public static final String SELECT_USER_BY_LOGIN = "SELECT u.*, ur.u_role_name FROM user u "
			+ "INNER JOIN user_role ur ON ur.u_role_id = u.u_role WHERE u_login = ?";

	public static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT u.*, ur.u_role_name FROM user u "
			+ "INNER JOIN user_role ur ON ur.u_role_id = u.u_role WHERE u_login = ? AND u_password = ?";

	public UserDaoImpl() {

	}

	@Override
	public void saveRegistrationInfo(RegistrationInfo registrationInfo) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(INSERT_USER);
			ps.setString(1, registrationInfo.getUser().getLogin());
			ps.setString(2, registrationInfo.getPassword());
			ps.setString(3, registrationInfo.getEmail());
			ps.setInt(4, USER_ROLE_ID);
			ps.executeUpdate();

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps);
		}

	}

	@Override
	public RegistrationInfo getRegistrationInfoByLogin(String login) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		RegistrationInfo registrationInfo = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_USER_BY_LOGIN);
			ps.setString(1, login);

			rs = ps.executeQuery();

			registrationInfo = getUser(rs, registrationInfo);

		} catch (SQLException |

				ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}

		return registrationInfo;
	}

	@Override
	public RegistrationInfo getRegistrationInfoByLoginAndPassword(String login, String password) throws DaoExeption {
		ConnectionPool connectionPool = ConnectionPool.getInstance();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		RegistrationInfo registrationInfo = null;

		try {
			con = connectionPool.takeConnection();
			ps = con.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
			ps.setString(1, login);
			ps.setString(2, password);

			rs = ps.executeQuery();

			registrationInfo = getUser(rs, registrationInfo);

		} catch (SQLException |

				ConnectionPoolException e) {
			throw new DaoExeption(e);
			// TODO log
		} finally {
			connectionPool.closseConnection(con, ps, rs);
		}

		return registrationInfo;
	}

	private RegistrationInfo getUser(ResultSet rs, RegistrationInfo registrationInfo) throws SQLException {

		while (rs.next()) {
			User user = new User();
			registrationInfo = new RegistrationInfo();

			user.setId(rs.getLong("u_id"));
			user.setLogin(rs.getString("u_login"));
			user.setRole(rs.getString("u_role_name"));
			registrationInfo.setUser(user);

			registrationInfo.setEmail(rs.getString("u_email"));
			registrationInfo.setPassword(rs.getString("u_password"));
		}

		return registrationInfo;
	}
}
