package com.epam.library.command.requestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ParameterSetter {
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String LANGUAGE = "language";
	private static final String TYPE_OF_BOOK = "typeOfBook";

	public static void loginMappingRequest(HttpServletRequest request, HttpSession session) {
		if ((request.getParameter(USER_NAME) != null) || (request.getAttribute(PASSWORD) != null)) {

			session.setAttribute(USER_NAME, request.getParameter(USER_NAME));
			session.setAttribute(PASSWORD, request.getParameter(PASSWORD));
		}
	}

	public static void setLanguage(HttpServletRequest request, HttpSession session) {

		String typeBook = (String) request.getParameter(LANGUAGE);
		if (typeBook != null && !typeBook.isEmpty()) {

			session.setAttribute(LANGUAGE, request.getParameter(LANGUAGE));
		}
	}

	public static void setTypeOfBook(HttpServletRequest request, HttpSession session) {
		

		String typeBook = (String) request.getParameter("typeOfBook");
		if (typeBook != null && !typeBook.isEmpty()) {
			
			session.setAttribute(TYPE_OF_BOOK, request.getParameter(TYPE_OF_BOOK));
		}
	}
	public static void setIdOfSelectedBook(HttpServletRequest request, HttpSession session) {
		

		String bookId = (String) request.getParameter("bookId");
		if (bookId != null && !bookId.isEmpty()) {
			
			request.setAttribute("bookId", request.getParameter("bookId"));
		}
	}

public static void setAction(HttpServletRequest request, HttpSession session) {
		

		String actionName = (String) request.getParameter("action");
		if (actionName != null && !actionName.isEmpty() ) {
		
			session.setAttribute("action", request.getParameter("action"));
		}
	}

}
