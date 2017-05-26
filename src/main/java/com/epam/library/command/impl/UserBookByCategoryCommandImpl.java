package com.epam.library.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserBookByCategoryCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UserBookByCategoryCommandImpl.class);
	private static final String IS_ALL_SELECTED = "isAll";
	private static final String ALL_SELECTED = "ALL";
	private static final String ACTION = "action";
	private static final String USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}

		String typeBook = (String) request.getParameter(FormParamEnum.TYPE_OF_BOOK.getParam());
		if (typeBook != null && !typeBook.isEmpty()) {

			session.setAttribute(FormParamEnum.TYPE_OF_BOOK.getParam(),
					request.getParameter(FormParamEnum.TYPE_OF_BOOK.getParam()));
		}

		String role = (String) request.getParameter(USER);

		if (role != null && !role.isEmpty()) {

			session.setAttribute(USER, request.getParameter(USER));
		}
		List<List<Book>> electronicBookList = new ArrayList<>();

		DisplayBookDTO userRequested = new DisplayBookDTO();

		userRequested.setTypeOfBook((String) session.getAttribute(FormParamEnum.TYPE_OF_BOOK.getParam()));
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		if (userRequested.getTypeOfBook().equals(ALL_SELECTED)) {
			request.setAttribute(IS_ALL_SELECTED, "true");

		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			electronicBookList = service.getBookList(userRequested);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.toString());
			logger.log(Level.ERROR, "Exception occured", e);
		}

		request.setAttribute(FormParamEnum.BOOK_INFO.getParam(), electronicBookList);

		if (session.getAttribute(USER) != null) {

			return TargetPage.ADMIN_PAGE.getParam();
		} else {
			System.out.println("user");
			return TargetPage.USER_PAGE.getParam();
		}

	}
}
