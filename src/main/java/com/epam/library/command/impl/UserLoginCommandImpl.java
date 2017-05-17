package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;



public class UserLoginCommandImpl implements Command {

	private static Logger logger = Logger.getLogger(UserLoginCommandImpl.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String view = null;
		Request userRequest = new Request();
		User retrievedUserInfo = new User();
		String actionName = request.getParameter(LoginParamEnum.ACTION.getParam());
		userRequest.setUserName((String) request.getAttribute(LoginParamEnum.USER_NAME.getParam()));
		userRequest.setPassword((String) request.getAttribute(LoginParamEnum.PASSWORD.getParam()));
		userRequest.setLanguage((String) session.getAttribute(LoginParamEnum.LANGUAGE.getParam()));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			retrievedUserInfo = service.authenticateUser(userRequest, actionName);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured", e);

		}

		if (retrievedUserInfo.getUserRole() != null) {
			session.setAttribute(LoginParamEnum.USER_INFO.getParam(), retrievedUserInfo);

			if (retrievedUserInfo.getUserRole().equals(LoginParamEnum.ADMIN.getParam())) {
				session.setAttribute(LoginParamEnum.ADMIN_ROLE.getParam(), "true");

				session.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				session.setAttribute("toForwarded", LoginParamEnum.REDIRECT.getParam());

				view = LoginParamEnum.WELCOME_PAGE.getParam();

			} else if (retrievedUserInfo.getUserRole().equals(LoginParamEnum.USER.getParam())) {

				session.setAttribute(LoginParamEnum.USER_ROLE.getParam(), "true");
				session.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				session.setAttribute("toForwarded", LoginParamEnum.REDIRECT.getParam());
				view = LoginParamEnum.WELCOME_PAGE.getParam();
			}
		} else {

			request.setAttribute(LoginParamEnum.ERROR_MESSAGE.getParam(), LoginParamEnum.DATA_NOT_FOUND.getParam());
			request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
					LoginParamEnum.FORWARD_REQUEST.getParam());

			view = ParamEnum.LOGIN_PAGE.getParam();

		}

		return view;
	}

}
