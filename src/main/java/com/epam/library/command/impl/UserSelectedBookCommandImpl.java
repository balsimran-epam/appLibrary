package com.epam.library.command.impl;

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

public class UserSelectedBookCommandImpl implements Command {

	private static Logger logger = Logger.getLogger(UserSelectedBookCommandImpl.class);

	private static final String BOOK_ID = "bookId";
	private static final String ALL_BOOK = "selected";
	private static final String SELECTED_BOOK_INFO = "selectedBookInfo";
	private static final String IS_ALL_SELECTED = "isAll";
	private static final String ALL_SELECTED = "ALL";
	private static final String FLAG_TRUE = "true";
	private static final String ACTION = "action";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}

		String bookId = (String) request.getParameter(BOOK_ID);
		if (bookId != null && !bookId.isEmpty()) {

			session.setAttribute(BOOK_ID, request.getParameter(BOOK_ID));
		
		}
		String typeBook = (String) request.getParameter(FormParamEnum.TYPE_OF_BOOK.getParam());
		if (typeBook != null && !typeBook.isEmpty()) {

			session.setAttribute(FormParamEnum.TYPE_OF_BOOK.getParam(),
					request.getParameter(FormParamEnum.TYPE_OF_BOOK.getParam()));
		}

		Book electronicBookList = new Book();
		DisplayBookDTO userRequested = new DisplayBookDTO();
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) (String) session.getAttribute(FormParamEnum.TYPE_OF_BOOK.getParam()));
		if (userRequested.getType().equals(ALL_SELECTED)) {

			request.setAttribute(IS_ALL_SELECTED, FLAG_TRUE);

		}

		userRequested.setBookId((String) session.getAttribute(BOOK_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			electronicBookList = service.getBook(userRequested);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);
		}

		request.setAttribute(SELECTED_BOOK_INFO, electronicBookList);
		return TargetPage.BOOK_INFO_PAGE.getParam();

	}

}
