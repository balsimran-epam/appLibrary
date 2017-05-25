package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class WelcomeToEditCommad implements Command {
	private static final String TYPE_TO_BE_EDITED = "bookTypeToBeEdited";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String typeOfBook = null;
		HttpSession session = request.getSession();

		String viewToForward = null;

		if (session.getAttribute(TYPE_TO_BE_EDITED) != null) {

			typeOfBook = (String) session.getAttribute(TYPE_TO_BE_EDITED);
			viewToForward = TargetPage.EDIT_BOOK_PAGE.getParam();

		}
		return viewToForward;
	}

}
