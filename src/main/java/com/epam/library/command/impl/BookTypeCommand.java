package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class BookTypeCommand implements Command {
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType";
	private static final String TYPE_TO_BE_ADDED = "bookTypeToBeAdded";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String role = (String) request.getParameter(TYPE_TO_BE_ADDED);

		if (role != null && !role.isEmpty()) {

			session.setAttribute(TYPE_TO_BE_ADDED, request.getParameter(TYPE_TO_BE_ADDED));
		}

		
		return ADMIN_FORM_COMMAND;
	}
}
