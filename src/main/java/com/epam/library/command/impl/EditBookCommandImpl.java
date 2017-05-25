package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
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

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		String typeOfBook = null;
		Book electronicBookList = new Book();

		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeEdited(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);

		ParameterSetter.setAction(request, session);

		Request userRequested = new Request();
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) session.getAttribute(TYPE_TO_BE_EDITED));
		if (userRequested.getType().equals(ALL_SELECTED)) {

			request.setAttribute(IS_ALL_SELECTED, "true");

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

		if (session.getAttribute(TYPE_TO_BE_EDITED) != null) {

			typeOfBook = (String) session.getAttribute(TYPE_TO_BE_EDITED);

		}
		return ADMIN_FORM_COMMAND;
	}
}
