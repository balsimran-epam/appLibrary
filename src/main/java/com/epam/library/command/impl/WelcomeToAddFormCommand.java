package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class WelcomeToAddFormCommand implements Command {
	private static final String BOOK_TO_BE_ADDED = "bookTypeToBeAdded";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)  {

		HttpSession session = request.getSession();
		String viewToForward = null;

		if (session.getAttribute(BOOK_TO_BE_ADDED) != null) {
			viewToForward = TargetPage.ADD_BOOK_PAGE.getParam();
		}
		return viewToForward;

	}
}
