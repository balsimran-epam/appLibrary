package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class EditBookCommandImpl implements Command {
	private static final String BOOK_ID = "bookId";
	private static final String ALL_BOOK = "selected";
	private static final String SELECTED_BOOK_INFO = "selectedBookInfo";
	private static final String ADMIN_FORM_COMMAND = "ControllerServlet?action=editType";
	private static final String TYPE_TO_BE_EDITED= "bookTypeToBeEdited";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		String typeOfBook = null;
		Book electronicBookList = new Book();
		HttpSession session = request.getSession();
		ParameterSetter.storingTypeOFBookToBeEdited(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		ParameterSetter.setLanguage(request, session);
		ParameterSetter.setAction(request, session);

		Request userRequested = new Request();
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) session.getAttribute(TYPE_TO_BE_EDITED));
		if (userRequested.getType().equals("ALL")) {

			request.setAttribute("isAll", "true");
			session.getAttribute("isAll");
		}
	
		userRequested.setBookId((String) session.getAttribute(BOOK_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		electronicBookList = service.getBook(userRequested);
		request.setAttribute(SELECTED_BOOK_INFO, electronicBookList);
	
		if (session.getAttribute(TYPE_TO_BE_EDITED) != null) {

			typeOfBook = (String) session.getAttribute(TYPE_TO_BE_EDITED);
			

		}
		return ADMIN_FORM_COMMAND;
	}
}
