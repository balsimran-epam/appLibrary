package com.epam.library.command.requestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.library.domain.EditUserDTO;

public class ParameterSetter {
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";

	private static final String USER_ID = "userId";
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";

	public static void loginMappingRequest(HttpServletRequest request, HttpSession session) {
		if ((request.getParameter(USER_NAME) != null) || (request.getAttribute(PASSWORD) != null)) {

			session.setAttribute(USER_NAME, request.getParameter(USER_NAME));
			session.setAttribute(PASSWORD, request.getParameter(PASSWORD));
		}
	}

	public static void setUserId(HttpServletRequest request, HttpSession session) {

		String userId = (String) request.getParameter(USER_ID);
		if (userId != null && !userId.isEmpty()) {

			session.setAttribute(USER_ID, request.getParameter(USER_ID));
			request.setAttribute(USER_ID, request.getParameter(USER_ID));
		}
	}

	public static void storingTypeOFBookToBeAdded(HttpServletRequest request, HttpSession session) {

		String role = (String) request.getParameter("bookTypeToBeAdded");

		if (role != null && !role.isEmpty()) {

			session.setAttribute("bookTypeToBeAdded", request.getParameter("bookTypeToBeAdded"));
		}
	}

	public static void storingTypeOFBookToBeEdited(HttpServletRequest request, HttpSession session) {

		String role = (String) request.getParameter("bookTypeToBeEdited");

		if (role != null && !role.isEmpty()) {

			session.setAttribute("bookTypeToBeEdited", request.getParameter("bookTypeToBeEdited"));
		}
	}

	public static void storingUserInSession(HttpServletRequest request, HttpSession session, EditUserDTO user) {
		session.setAttribute(FIRST_NAME, user.getFirstName());

		session.setAttribute(LAST_NAME, user.getLastName());
		session.setAttribute(EMAIL, user.getEmail());
		session.setAttribute(STREET_ADDRESS, user.getStreetAddress());
		session.setAttribute(LOCALITY_ADDRESS, user.getLocalityAddress());

	}
}
