package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class WelcomeToUserCommand implements Command {
	private static final String FLAG_TRUE = "true";
	private static final String ACTION = "action";
	private static final String PAGE_NOT_FOUND = "NOT FOUND PAGE";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}
		String viewToForward = null;
		if (session.getAttribute(FormParamEnum.ADMIN_ROLE.getParam()) != null) {
			if ((session.getAttribute(FormParamEnum.ADMIN_ROLE.getParam())).equals(FLAG_TRUE)) {
				viewToForward = TargetPage.ADMIN_PAGE.getParam();
				return viewToForward;
			}
		} else if ((session.getAttribute(FormParamEnum.USER_ROLE.getParam()) != null)
				&& (session.getAttribute(FormParamEnum.USER_ROLE.getParam()).equals(FLAG_TRUE))) {

			viewToForward = TargetPage.USER_PAGE.getParam();
			return viewToForward;
		}

		return PAGE_NOT_FOUND;
	}
}
