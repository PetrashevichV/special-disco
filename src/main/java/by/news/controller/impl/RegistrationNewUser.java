package by.news.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;

import by.news.bean.RegistrationInfo;
import by.news.bean.User;
import by.news.controller.Command;
import by.news.controller.validator.EmailValidator;
import by.news.controller.validator.LoginValidator;
import by.news.controller.validator.PasswordValidator;
import by.news.controller.validator.ValidatorProvider;
import by.news.service.ServiceExeption;
import by.news.service.ServiceProvider;
import by.news.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationNewUser implements Command {
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final UserService userService = provider.getUserService();

	private static final ValidatorProvider validator = ValidatorProvider.getInstance();

	private static final String REQUEST_PARAMETR_LOGIN = "login";
	private static final String REQUEST_PARAMETR_EMAIL = "email";
	private static final String REQUEST_PARAMETR_PASSWORD = "password";
	private static final String REQUEST_PARAMETR_CONFIRM_PASSWORD = "confirmPassword";

	private static final String REDIRECT_REGISTRATION_ERROR_PATH = "frontController?command=registration&message_error=";
	private static final String ERROR_LOGIN = "Login is busy";
	private static final String REDIRECT_AUTHORIZATION_MESSAGE_PATH = "frontController?command=authorization&message=registration completed successfully";
	private static final String REDIRECT_UNKNOWN_COMMAND_PATH = "frontController?command=unknown_command";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(REQUEST_PARAMETR_LOGIN).toLowerCase();
		String email = request.getParameter(REQUEST_PARAMETR_EMAIL);
		String password = request.getParameter(REQUEST_PARAMETR_PASSWORD);
		String confirmPassword = request.getParameter(REQUEST_PARAMETR_CONFIRM_PASSWORD);

		String message = validator.validateRegistrationInfo(login, email, password, confirmPassword);
		if (message.length() != 0) {
			response.sendRedirect(REDIRECT_REGISTRATION_ERROR_PATH + message);
			return;
		}

		try {

			if (userService.isLoginEmpty(login)) {
				User user = new User(login);
				RegistrationInfo registrationInfo = new RegistrationInfo(user, password, email);
				userService.registration(registrationInfo);
			} else {
				response.sendRedirect(REDIRECT_REGISTRATION_ERROR_PATH + ERROR_LOGIN);
				return;
			}

			response.sendRedirect(REDIRECT_AUTHORIZATION_MESSAGE_PATH);
		} catch (ServiceExeption e) {
			// log + eror.jsp
			response.sendRedirect(REDIRECT_UNKNOWN_COMMAND_PATH);
		}

	}

}
