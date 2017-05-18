package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class RedirectServlet implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
				LoginParamEnum.FORWARD_REQUEST.getParam());
		HttpSession session = request.getSession();
		String viewToForward = null;
		if (session.getAttribute(LoginParamEnum.ADMIN_ROLE.getParam()) != null) {
			if ((session.getAttribute(LoginParamEnum.ADMIN_ROLE.getParam())).equals("true")) {
				viewToForward = LoginParamEnum.ADMIN_PAGE.getParam();
				return viewToForward;
			}
		} else if ((session.getAttribute(LoginParamEnum.USER_ROLE.getParam()) != null)
				&& (session.getAttribute(LoginParamEnum.USER_ROLE.getParam()).equals("true"))) {

			viewToForward = LoginParamEnum.USER_PAGE.getParam();
			return viewToForward;
		}

		return "NOT FOUND PAGE";
	}
}
