package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.EditUserDTO;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class TranslateUserCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(TranslateUserCommandImpl.class);
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";
	private static final String USER_ID = "userId";
	private static final String USER_WELCOME_REQUEST = "ControllerServlet?action=WelcomeToAdd";
	private static final String IS_TRANSLATED = "isTranslatedUser";
	private static final String IS_UPDATED = "isUpdatedUser";
	private static final String ACTION = "action";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		boolean istTanslated = false;
		HttpSession session = request.getSession();
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}
		
		EditUserDTO user = new EditUserDTO();
		String language = (String) session.getAttribute(FormParamEnum.LANGUAGE.getParam());
		user.setFirstName((String) session.getAttribute(FIRST_NAME));
		user.setLastName((String) session.getAttribute(LAST_NAME));
		user.setEmail((String) session.getAttribute(EMAIL));
		user.setStreetAddress((String) session.getAttribute(STREET_ADDRESS));
		user.setLocalityAddress((String) session.getAttribute(LOCALITY_ADDRESS));
		user.setUserId(Integer.parseInt((String) session.getAttribute(USER_ID)));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			istTanslated = service.translateUser(user, language);
		} catch (ServiceException e) {

			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);

		}
		if (istTanslated) {

			session.setAttribute("userInfo", user);
		}
		System.out.println(istTanslated);
		session.setAttribute(IS_UPDATED, "true");
		session.setAttribute(IS_TRANSLATED, istTanslated);
		return USER_WELCOME_REQUEST;
	}

}
