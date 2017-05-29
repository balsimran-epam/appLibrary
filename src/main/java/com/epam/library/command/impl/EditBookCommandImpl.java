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

public class EditBookCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(EditBookCommandImpl.class);
	private static final String BOOK_ID = "bookId";
	private static final String ALL_BOOK = "selected";
	private static final String SELECTED_BOOK_INFO = "selectedBookInfo";
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=editType";
	private static final String TYPE_TO_BE_EDITED = "bookTypeToBeEdited";
	private static final String IS_ALL_SELECTED = "isAll";
	private static final String ALL_SELECTED = "ALL";
	private static final String ACTION = "action";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		Book electronicBookList = new Book();

		HttpSession session = request.getSession();

		String typeOfBook = (String) request.getParameter(TYPE_TO_BE_EDITED);

		if (typeOfBook != null && !typeOfBook.isEmpty()) {

			session.setAttribute(TYPE_TO_BE_EDITED, request.getParameter(TYPE_TO_BE_EDITED));
		}

		String bookId = (String) request.getParameter(BOOK_ID);
		if (bookId != null && !bookId.isEmpty()) {

			request.setAttribute(BOOK_ID, request.getParameter(BOOK_ID));
		}

		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}
		DisplayBookDTO userRequested = new DisplayBookDTO();
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));

		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) session.getAttribute(TYPE_TO_BE_EDITED));
		if (userRequested.getType().equals(ALL_SELECTED)) {

			request.setAttribute(IS_ALL_SELECTED, "true");
		}
		userRequested.setBookId((String) request.getAttribute(BOOK_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			electronicBookList = service.getBook(userRequested);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);
		}
		request.setAttribute(SELECTED_BOOK_INFO, electronicBookList);

		return ADMIN_FORM_COMMAND;
	}
}
