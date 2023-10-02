package by.news.controller.validator;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator {
	private static final Pattern pattern = Pattern.compile("\\w{2,25}");

	@Override
	public boolean validate(String strLogin) {
		return pattern.matcher(strLogin.trim()).matches();
	}

}
