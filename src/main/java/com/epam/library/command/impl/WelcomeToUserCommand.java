package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;

public class WelcomeToUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
	
	
		String viewToForward = null;
		if (session.getAttribute(FormParamEnum.ADMIN_ROLE.getParam()) != null) {
			if ((session.getAttribute(FormParamEnum.ADMIN_ROLE.getParam())).equals("true")) {
				viewToForward = TargetPage.ADMIN_PAGE.getParam();
				return viewToForward;
			}
		} else if ((session.getAttribute(FormParamEnum.USER_ROLE.getParam()) != null)
				&& (session.getAttribute(FormParamEnum.USER_ROLE.getParam()).equals("true"))) {

			viewToForward = TargetPage.USER_PAGE.getParam();
			return viewToForward;
		}

		return "NOT FOUND PAGE";
	}
}
