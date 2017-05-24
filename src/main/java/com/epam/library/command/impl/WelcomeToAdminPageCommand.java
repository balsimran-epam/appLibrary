package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.service.exception.ServiceException;

public class WelcomeToAdminPageCommand implements Command {
	private static final String INSERTED_RECORD_MESSAGE = "inserted";

	private static final String UPDATED_RECORD_MESSAGE = "isUpdatedBook";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeAddes(request, session);

		String viewToForward = null;
		if ((session.getAttribute(INSERTED_RECORD_MESSAGE) != null)
				|| (session.getAttribute(UPDATED_RECORD_MESSAGE) != null)) {

			viewToForward = TargetPage.ADMIN_PAGE.getParam();

		}
		return viewToForward;
	}
}
