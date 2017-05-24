package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.service.exception.ServiceException;

public class BookTypeCommand implements Command {
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType";
	private static final String TYPE_TO_BE_ADDED = "bookTypeToBeAdded";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		String typeOfBook = null;
		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeAddes(request, session);

		String viewToForward = null;
		if (session.getAttribute(TYPE_TO_BE_ADDED) != null) {
			typeOfBook = (String) session.getAttribute("bookTypeToBeAdded");
			viewToForward = "WEB-INF/jsp/addPBook.jsp";

		}
		return ADMIN_FORM_COMMAND;
	}
}
