package com.epam.library.command.requestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.epam.library.domain.EditUserDTO;

public class UserSetterIntoSession {
	private static final String FIRST_NAME = "firstName";
	private static final String LAST_NAME = "lastName";
	private static final String EMAIL = "email";
	private static final String STREET_ADDRESS = "streetAddress";
	private static final String LOCALITY_ADDRESS = "localityAddress";

	public static void storingUserInSession(HttpServletRequest request, HttpSession session, EditUserDTO user) {
		session.setAttribute(FIRST_NAME, user.getFirstName());

		session.setAttribute(LAST_NAME, user.getLastName());
		session.setAttribute(EMAIL, user.getEmail());
		session.setAttribute(STREET_ADDRESS, user.getStreetAddress());
		session.setAttribute(LOCALITY_ADDRESS, user.getLocalityAddress());

	}
}
