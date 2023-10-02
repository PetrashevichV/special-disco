package by.news.controller.impl;

import java.io.IOException;
import java.nio.file.Path;

import by.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToRegistration implements Command {
	private static final String PATH = "/WEB-INF/jsp/registration.jsp";
	private static final String URL = "frontController?command=registration";
	private static final String ATTRIBUTE_NAME_URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(PATH);
		request.getSession().setAttribute(ATTRIBUTE_NAME_URL, URL);
		requestDispatcher.forward(request, response);
	}

}
