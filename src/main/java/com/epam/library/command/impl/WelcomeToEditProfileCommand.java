package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.User;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class WelcomeToEditProfileCommand implements Command {
	private static Logger logger = Logger.getLogger(WelcomeToEditProfileCommand.class);
	private static final String USER_ID = "userId";
	private static final String RETRIEVED_USER = "retrievedUser";
	private static final String ACTION = "action";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String userId = (String) request.getParameter(USER_ID);
		if (userId != null && !userId.isEmpty()) {

			session.setAttribute(USER_ID, request.getParameter(USER_ID));
			request.setAttribute(USER_ID, request.getParameter(USER_ID));
		}
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}
		User user = new User();
		String language = (String) session.getAttribute(FormParamEnum.LANGUAGE.getParam());
		int id = (Integer.parseInt((String) session.getAttribute(USER_ID)));
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
