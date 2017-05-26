package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.LanguageSetter;

public class LibraryLanguageCommand implements Command {
	private static final String GO_TO_PREVIOUS_COMMAND = "ControllerServlet?action=";
	private static final String PREVIOUS_COMMAND = "previousCommand";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		LanguageSetter.setLanguage(request, session);

		String previousCommand = (String) request.getParameter(PREVIOUS_COMMAND);
	
		return GO_TO_PREVIOUS_COMMAND + previousCommand;

	}
}
