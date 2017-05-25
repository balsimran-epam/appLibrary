package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;

public class BookTypeCommand implements Command {
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType";
	private static final String TYPE_TO_BE_ADDED = "bookTypeToBeAdded";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String typeOfBook = null;
		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeAddes(request, session);

		if (session.getAttribute(TYPE_TO_BE_ADDED) != null) {
			typeOfBook = (String) session.getAttribute(TYPE_TO_BE_ADDED);

		}
		return ADMIN_FORM_COMMAND;
	}
}
