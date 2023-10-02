package by.news.controller.validator;

public class ValidatorProvider {

	private static final ValidatorProvider instance = new ValidatorProvider();

	private static final EmailValidator emailValidator = new EmailValidator();
	private static final LoginValidator loginValidator = new LoginValidator();
	private static final PasswordValidator passwordValidator = new PasswordValidator();

	private static final String EROR_MASSAGE_LOGIN = "Login must contain from 2 to 20 characters.";
	private static final String EROR_MASSAGE_EMAIL = " Wrong email.";
	private static final String EROR_MASSAGE_PASSWORD = " Password must contain from 2 to 25 characters.";
	private static final String EROR_MASSAGE_CONFIRM_PASSWORD = " Password don't match with confirm password";

	public static ValidatorProvider getInstance() {
		return instance;
	}

	public String validateRegistrationInfo(String login, String email, String password, String confirmPassword) {
		StringBuffer message = new StringBuffer();
		if (!loginValidator.validate(login)) {
			message.append(EROR_MASSAGE_LOGIN);
		}

		if (!emailValidator.validate(email)) {
			message.append(EROR_MASSAGE_EMAIL);
		}

		if (!passwordValidator.validate(password)) {
			message.append(EROR_MASSAGE_PASSWORD);
		}

		if (!password.equals(confirmPassword)) {
			message.append(EROR_MASSAGE_CONFIRM_PASSWORD);
		}
		return message.toString();
	}

	public String validateSingIn(String login, String password) {
		StringBuffer message = new StringBuffer();
		if (!loginValidator.validate(login)) {
			message.append(EROR_MASSAGE_LOGIN);
		}

		if (!passwordValidator.validate(password)) {
			message.append(EROR_MASSAGE_PASSWORD);
		}

		return message.toString();
	}
}
