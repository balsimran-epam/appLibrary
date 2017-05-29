package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.UserSetterIntoSession;
import com.epam.library.domain.EditUserDTO;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UpdateUserCommandImpl implements Command {

	private static Logger logger = Logger.getLogger(WelcomeToEditProfileCommand.class);
	private static final String USER_WELCOME_REQUEST = "ControllerServlet?action=WelcomeToAdd";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";
	private static final String USER_ID = "userId";
	private static final String IS_UPDATED = "isUpdatedUser";
	private static final String ACTION = "action";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) request.getParameter(USER_ID);
		if (id != null && !id.isEmpty()) {

			session.setAttribute(USER_ID, request.getParameter(USER_ID));
			request.setAttribute(USER_ID, request.getParameter(USER_ID));
		}
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}

		String userId = (String) request.getParameter(USER_ID);
		if (userId != null && !userId.isEmpty()) {

			session.setAttribute(USER_ID, request.getParameter(USER_ID));

		}

		boolean isUpdated = false;
		EditUserDTO user = new EditUserDTO();
		String language = (String) session.getAttribute(FormParamEnum.LANGUAGE.getParam());
		user.setFirstName(request.getParameter(FIRST_NAME));
		user.setLastName(request.getParameter(LAST_NAME));
		user.setEmail(request.getParameter(EMAIL));
		user.setStreetAddress(request.getParameter(STREET_ADDRESS));
		user.setLocalityAddress(request.getParameter(LOCALITY_ADDRESS));
		user.setUserId(Integer.parseInt((String) session.getAttribute(USER_ID)));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			isUpdated = service.updateUser(user, language);
		} catch (ServiceException e) {

			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);

		}

		if (isUpdated) {

			session.setAttribute("userInfo", user);
		} else {

			UserSetterIntoSession.storingUserInSession(request, session, user);
		}
		session.setAttribute(IS_UPDATED, isUpdated);

		return USER_WELCOME_REQUEST;
	}
}
