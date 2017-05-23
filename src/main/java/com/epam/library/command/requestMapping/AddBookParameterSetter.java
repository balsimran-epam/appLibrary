package com.epam.library.command.requestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddBookParameterSetter {
	
public static void setAction(HttpServletRequest request, HttpSession session) {
		

		String actionName = (String) request.getParameter("action");
		if (actionName != null && !actionName.isEmpty() ) {
		
			session.setAttribute("action", request.getParameter("action"));
		}
	}

public static void setTitle(HttpServletRequest request, HttpSession session) {
	

	String actionName = (String) request.getParameter("action");
	if (actionName != null && !actionName.isEmpty() ) {
	
		session.setAttribute("action", request.getParameter("action"));
	}
}

}
