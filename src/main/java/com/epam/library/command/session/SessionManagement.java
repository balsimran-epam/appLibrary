package com.epam.library.command.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionManagement {
	
	public  static HttpSession sessionCreate(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null)
			session.invalidate();
		return session = request.getSession();
	}
	
}
