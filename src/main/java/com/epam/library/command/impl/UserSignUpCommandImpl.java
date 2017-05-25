package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserSignUpCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UserSignUpCommandImpl.class);
	private static final String CHECK_HUMAN = "humancheck";
	
	private static final String NOT_MATCHED = "fail";
	private static final String HUMAN = "human";
	private static final String FIRST_NAME = "fName";
	private static final String LAST_NAME = "lName";
	private static final String EMAIL = "email";
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String RE_ENTERED_PASSWORD = "reenterpassword";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";
	private static final String PASSWORD_MATCHED_MESSAGE = "passwordMatch";
	private static final String DEFAULT_LANGUAGE = "en";
	private static final String INSERTED_RECORD_MESSAGE = "inserted";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		RegisteredUser registeredUser = new RegisteredUser();
		HttpSession session = request.getSession();
	
		ParameterSetter.setTypeOfBook(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		ParameterSetter.setAction(request, session);
	
		boolean isUserInserted = false;
		if (!(request.getParameter(PASSWORD)).equals(request.getParameter(RE_ENTERED_PASSWORD))) {
			request.setAttribute(PASSWORD_MATCHED_MESSAGE, NOT_MATCHED);
		} else {
			if (request.getParameter(CHECK_HUMAN) != null) {
				if ((request.getParameter(CHECK_HUMAN)).equals(HUMAN)) {

					registeredUser.setFirstName(request.getParameter(FIRST_NAME));
					registeredUser.setLastName(request.getParameter(LAST_NAME));
					registeredUser.setEmail(request.getParameter(EMAIL));
					registeredUser.setUserName(request.getParameter(USER_NAME));
					registeredUser.setPassword(request.getParameter(PASSWORD));
					registeredUser.setStreetAddress(request.getParameter(STREET_ADDRESS));
					registeredUser.setLocalityAddress(request.getParameter(LOCALITY_ADDRESS));
					registeredUser.setLanguage(DEFAULT_LANGUAGE);
					ServiceFactory serviceFactory = ServiceFactory.getInstance();
					UserService service = serviceFactory.getUserService();
					try {
						isUserInserted = service.saveUser(registeredUser);
					} catch (ServiceException e) {
			
						request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
						logger.log(Level.ERROR, "Exception", e);

					}
				}else {
				
					request.setAttribute(CHECK_HUMAN, NOT_MATCHED);
				}
			} 
		}
		request.setAttribute(INSERTED_RECORD_MESSAGE, isUserInserted);

		return TargetPage.HOME_PAGE.getParam();

	}

}
