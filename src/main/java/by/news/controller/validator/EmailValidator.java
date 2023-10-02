package by.news.controller.validator;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {
	 private static final Pattern pattern = Pattern.compile("\\w+@\\w+\\.[a-zA-Z]{2,4}");

	    @Override
	    public boolean validate(String strEmail) {
	        return pattern.matcher(strEmail.trim()).matches();
	    }
	}
