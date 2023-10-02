package by.news.controller.impl;

import java.io.IOException;

import by.news.bean.RegistrationInfo;
import by.news.controller.Command;
import by.news.controller.validator.ValidatorProvider;
import by.news.service.ServiceExeption;
import by.news.service.ServiceProvider;
import by.news.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignIn implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final ValidatorProvider validator = ValidatorProvider.getInstance();

	private static final String REQUEST_PARAMETR_LOGIN = "login";
	private static final String REQUEST_PARAMETR_PASSWORD = "password";
	private static final String SESSION_ATTRIBUTE_USER = "user";

	private static final String REDIRECT_AUTHORIZATION_ERROR_PATH = "frontController?command=authorization&message_error=";
	private static final String ERROR_LOGIN_PASSWORD = "Wrong login or password";
	private static final String REDIRECT_GO_TO_AUTH_USER_PAGE = "frontController?command=go_to_auth_user_page";
	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(REQUEST_PARAMETR_LOGIN).toLowerCase();
		String password = request.getParameter(REQUEST_PARAMETR_PASSWORD);

		String message = validator.validateSingIn(login, password);
		if (message.length() != 0) {
			response.sendRedirect(REDIRECT_AUTHORIZATION_ERROR_PATH + message);
			return;
		}

		try {

			RegistrationInfo registrationInfo = userService.authorithation(login, password);

			if (registrationInfo == null) {
				response.sendRedirect(REDIRECT_AUTHORIZATION_ERROR_PATH + ERROR_LOGIN_PASSWORD);
				return;
			}

			HttpSession session = request.getSession(true);
			session.setAttribute(SESSION_ATTRIBUTE_USER, registrationInfo.getUser());

			response.sendRedirect(REDIRECT_GO_TO_AUTH_USER_PAGE);
		} catch (ServiceExeption e) {
			// log
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
		}

	}
}
