package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.service.exception.ServiceException;

public class WelcomeToEditCommad implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		System.out.println("in last edit");
		String typeOfBook = null;
		HttpSession session = request.getSession();

		String viewToForward = null;

		if (session.getAttribute("bookTypeToBeEdited") != null) {
			System.out.println("not null");
			typeOfBook = (String) session.getAttribute("bookTypeToBeEdited");
			viewToForward = "WEB-INF/jsp/editBook.jsp";

		}
		return viewToForward;
	}

}
