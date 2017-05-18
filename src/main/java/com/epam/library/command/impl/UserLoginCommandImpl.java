package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.LoginRequestMapping;
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
		LoginRequestMapping.loginMappingRequest(request,session);
		LoginRequestMapping.setLanguage(request, session);
	
		LoginRequestMapping.setAction(request, session);
		String actionName = request.getParameter(LoginParamEnum.ACTION.getParam());
		userRequest.setUserName((String) session.getAttribute(LoginParamEnum.USER_NAME.getParam()));
		userRequest.setPassword((String) session.getAttribute(LoginParamEnum.PASSWORD.getParam()));
		userRequest.setLanguage((String) session.getAttribute(LoginParamEnum.LANGUAGE.getParam()));

	String toPassUserName=userRequest.getUserName();
	
	String toWelcome="Welcome";
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			retrievedUserInfo = service.authenticateUser(userRequest, actionName);
		} catch (ServiceException e) {
			request.setAttribute("exceptionOccured", e.toString());
			
			logger.log(Level.ERROR, "Exception occured", e);
		

		}
System.out.println(retrievedUserInfo.getName());
		if (retrievedUserInfo.getUserRole() != null) {
			session.setAttribute(LoginParamEnum.USER_INFO.getParam(), retrievedUserInfo);

			if (retrievedUserInfo.getUserRole().equals(LoginParamEnum.ADMIN.getParam())) {
				session.setAttribute(LoginParamEnum.ADMIN_ROLE.getParam(), "true");

				session.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				session.setAttribute("toForwarded", LoginParamEnum.REDIRECT.getParam());

				view = "HomeServlet?action="+toWelcome+"&userName="+toPassUserName ;

			} else if (retrievedUserInfo.getUserRole().equals(LoginParamEnum.USER.getParam())) {

				session.setAttribute(LoginParamEnum.USER_ROLE.getParam(), "true");
				session.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
						ParamEnum.SEND_REDIRECT_REQUEST.getParam());
				session.setAttribute("toForwarded", LoginParamEnum.REDIRECT.getParam());
				
				view = "HomeServlet?action="+toWelcome+"&userName="+toPassUserName ;
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
