package com.epam.library.command.requestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LanguageSetter {
	
	private static final String LANGUAGE = "language";
	public static void setLanguage(HttpServletRequest request, HttpSession session) {

		String typeBook = (String) request.getParameter(LANGUAGE);
		if (typeBook != null && !typeBook.isEmpty()) {

			session.setAttribute(LANGUAGE, request.getParameter(LANGUAGE));
		}
	}

}
