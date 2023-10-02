package by.news.dao;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;

public interface UserDao {

	public void saveRegistrationInfo(RegistrationInfo registrationInfo) throws DaoExeption;

	public RegistrationInfo getRegistrationInfoByLogin(String login) throws DaoExeption;

	public RegistrationInfo getRegistrationInfoByLoginAndPassword(String login, String password) throws DaoExeption;

}
