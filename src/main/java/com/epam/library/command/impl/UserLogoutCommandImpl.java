package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;

public class UserLogoutCommandImpl implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			 {

		HttpSession sess = request.getSession(false);
		if (sess != null) {
			sess.invalidate();
		}

		request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(), ParamEnum.SEND_REDIRECT_REQUEST.getParam());
		String view = ParamEnum.LOGIN_PAGE.getParam();
		return view;

	}

}
