package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;
import com.epam.library.service.LoginService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserLoginCommandImpl implements Command {

	private static Logger logger = Logger.getLogger(UserLoginCommandImpl.class);
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String FLAG = "flag";
	private static final String USER_WELCOME_REQUEST = "ControllerServlet?action=Welcome&userName=";
	private static final String FLAG_TRUE = "true";

	@Override

	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String view = null;
		Request userRequest = new Request();
		User retrievedUserInfo = new User();
		ParameterSetter.loginMappingRequest(request, session);
	

		ParameterSetter.setAction(request, session);
		String actionName = request.getParameter(FormParamEnum.ACTION.getParam());
		userRequest.setUserName((String) session.getAttribute(USER_NAME));
		userRequest.setPassword((String) session.getAttribute(PASSWORD));
		userRequest.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		String toPassUserName = userRequest.getUserName();
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LoginService service = serviceFactory.getLoginService();
		try {
			retrievedUserInfo = service.authenticateUser(userRequest, actionName);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.toString());
			logger.log(Level.ERROR, "Exception occured", e);

		}

		if (retrievedUserInfo.getUserRole() != null) {
			session.setAttribute(FormParamEnum.USER_INFO.getParam(), retrievedUserInfo);

			if (retrievedUserInfo.getUserRole().equals(FormParamEnum.ADMIN.getParam())) {
			
				session.setAttribute(FormParamEnum.ADMIN_ROLE.getParam(), FLAG_TRUE);

				return USER_WELCOME_REQUEST + toPassUserName;

			} else if (retrievedUserInfo.getUserRole().equals(FormParamEnum.USER.getParam())) {

				session.setAttribute(FormParamEnum.USER_ROLE.getParam(), FLAG_TRUE);
				return USER_WELCOME_REQUEST + toPassUserName;
			}
		} else {

			request.setAttribute(FormParamEnum.ERROR_MESSAGE.getParam(), FormParamEnum.DATA_NOT_FOUND.getParam());
			request.setAttribute(FLAG, "true");
			view = TargetPage.LOGIN_PAGE.getParam();

		}

		return view;
	}

}
