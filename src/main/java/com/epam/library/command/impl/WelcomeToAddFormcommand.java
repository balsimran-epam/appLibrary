package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.service.exception.ServiceException;

public class WelcomeToAddFormcommand implements Command {
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType&typeOfBookToBeAdded=";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		System.out.println("in add");
		String typeOfBook = null;
		HttpSession session = request.getSession();

		String viewToForward = null;

		if (session.getAttribute("bookTypeToBeAdded") != null) {
			typeOfBook = (String) session.getAttribute("bookTypeToBeAdded");
			viewToForward = "WEB-INF/jsp/addPBook.jsp";

		}
		return viewToForward;

	}
}
