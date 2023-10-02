package by.news.dao.conection_pool.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
	public static final String DB_URL = "db.url";
	public static final String DB_LOGIN = "db.login";
	public static final String DB_PASSWORD = "db.password";
	public static final String DB_POOLSIZE = "db.poolsize";
	public static final String DB_LIMIT = "db.limit";
	public static final String PATH_TO_DAO_PROPERTIES = "/resources/dao.properties";

	private static Properties properties = new Properties();

	public static synchronized String getProperty(String name) {
		if (properties.isEmpty()) {
			try (InputStream is = Config.class.getClassLoader().getResourceAsStream(PATH_TO_DAO_PROPERTIES)) {
				properties.load(is);
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
				// TODO
			}
		}
		return properties.getProperty(name);
	}
}