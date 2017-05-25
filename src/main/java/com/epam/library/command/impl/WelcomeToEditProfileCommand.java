package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.EditUserDTO;
import com.epam.library.domain.User;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class WelcomeToEditProfileCommand implements Command {
	private static Logger logger = Logger.getLogger(WelcomeToEditProfileCommand.class);
	private static final String FIRST_NAME = "fName";
	private static final String LAST_NAME = "lName";
	private static final String EMAIL = "email";

	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";

	private static final String DEFAULT_LANGUAGE = "en";
	private static final String INSERTED_RECORD_MESSAGE = "inserted";
	private static final String USER_ID = "userId";
	private static final String RETRIEVED_USER = "retrievedUser";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		ParameterSetter.setUserId(request, session);
		ParameterSetter.setAction(request, session);
		String typeOfBook = null;
		User user = new User();
		String language = (String) session.getAttribute(FormParamEnum.LANGUAGE.getParam());
		int id = (Integer.parseInt((String) session.getAttribute(USER_ID)));

		/*
		 * user.setFirstName(request.getParameter(FIRST_NAME));
		 * user.setLastName(request.getParameter(LAST_NAME));
		 * user.setEmail(request.getParameter(EMAIL));
		 * user.setStreetAddress(request.getParameter(STREET_ADDRESS));
		 * user.setLocalityAddress(request.getParameter(LOCALITY_ADDRESS));
		 * user.setUserId((int) session.getAttribute(USER_ID));
		 */
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			user = service.getUserData(id, language);
		} catch (ServiceException e) {

			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);

		}
		request.setAttribute(RETRIEVED_USER, user);
		System.out.println(user.getFirstName());
		return TargetPage.EDIT_PROFILE.getParam();
	}
}
