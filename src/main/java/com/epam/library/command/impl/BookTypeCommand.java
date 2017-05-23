package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.service.exception.ServiceException;

public class BookTypeCommand implements Command{
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=bookType&isPB=";
	
	
	
	private static final String FORM= "&isEB=";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		System.out.println("in add");
		String typeOfBook = null;
		HttpSession session = request.getSession();
	ParameterSetter.storingTypeOFBookToBeAddes(request, session);

		String viewToForward = null;
String pB = null,isEB = null;
		if (session.getAttribute("bookTypeToBeAdded") != null) {
			if((session.getAttribute("bookTypeToBeAdded")).equals("PB"))
			{
				pB="true";
			}
			else
				isEB="true";
				
			typeOfBook = (String) session.getAttribute("bookTypeToBeAdded");
			viewToForward = "WEB-INF/jsp/addPBook.jsp";

		}
		return ADMIN_FORM_COMMAND + pB ;
	}
	}


