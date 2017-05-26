package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;

public class BookTypeCommand implements Command {
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeAdded(request, session);

		
		return ADMIN_FORM_COMMAND;
	}
}
