package by.news.controller.impl;

import java.io.IOException;
import java.util.Enumeration;

import by.news.controller.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChangeLocale implements Command {

	private static final String ATTRIBUTE_NAME_URL = "url";
	private static final String ATTRIBUTE_NAME_LOCALE = "local";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(ATTRIBUTE_NAME_LOCALE, request.getParameter(ATTRIBUTE_NAME_LOCALE));
		String url = (String) request.getSession().getAttribute(ATTRIBUTE_NAME_URL);

//		 for test
//		Enumeration<String> ses = request.getSession().getAttributeNames();
//		while (ses.hasMoreElements()) {
//			String string = (String) ses.nextElement();
//			System.out.println(string + "-" + request.getSession().getAttribute(string));
//		}
//
//		Cookie[] cookies = request.getCookies();
//		System.out.println(cookies.length);
//		for (Cookie cook : cookies) {
//			System.out.println(cook.getName() + "-" + cook.getValue());
//		}

		response.sendRedirect(url);

	}

}
