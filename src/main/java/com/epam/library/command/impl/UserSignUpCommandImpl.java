package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.service.UserService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserSignUpCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UserSignUpCommandImpl.class);
	private static final String INSERT_USER_SUCCESS_MESSAGE = "User has been registered";
	private static final String INSERT_USER_FAIL_MESSAGE = "User has not been registered";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("kk");
		RegisteredUser registeredUser = new RegisteredUser();
		HttpSession session = request.getSession();
		ParameterSetter.setLanguage(request, session);
		ParameterSetter.setTypeOfBook(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		ParameterSetter.setAction(request, session);
		String message = null;
		ParameterSetter.setAction(request, session);
		boolean isUserInserted = false;
		registeredUser.setFirstName(request.getParameter("fName"));
		registeredUser.setLastName(request.getParameter("lName"));
		registeredUser.setEmail(request.getParameter("email"));
		registeredUser.setUserName(request.getParameter("userName"));
		registeredUser.setPassword(request.getParameter("password"));
		registeredUser.setStreetAddress(request.getParameter("streetAddress"));
		registeredUser.setLocalityAddress(request.getParameter("localityAddress"));

		registeredUser.setLanguage("en");
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService service = serviceFactory.getUserService();
		try {
			isUserInserted = service.saveUser(registeredUser);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);
		
		

		}
		message = (isUserInserted) ? INSERT_USER_SUCCESS_MESSAGE : INSERT_USER_FAIL_MESSAGE;
		session.setAttribute("inserted", isUserInserted);
		System.out.println("oo"+isUserInserted);
		return "home.jsp";

	}

}
