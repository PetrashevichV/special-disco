package by.news.controller.impl;

import java.io.IOException;

import by.news.controller.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogOut implements Command {

	private static final String REDIRECT_GO_TO_MAIN_PAGE = "frontController?command=go_to_main_page";
	private static final String SESSION_ATTRIBUTE_USER = "user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		session.removeAttribute(SESSION_ATTRIBUTE_USER);

		response.sendRedirect(REDIRECT_GO_TO_MAIN_PAGE);
	}

}
