package by.news.service;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;

public interface UserService {

	public void registration(RegistrationInfo info) throws ServiceExeption;

	public RegistrationInfo authorithation(String login, String password) throws ServiceExeption;

	public boolean isLoginEmpty(String login) throws ServiceExeption;

}
