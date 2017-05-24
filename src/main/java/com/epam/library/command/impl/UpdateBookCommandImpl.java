package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UpdateBookCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UpdateBookCommandImpl.class);
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String AUTHOR = "author";
	private static final String PRICE = "price";
	private static final String ITEM = "item";
	private static final String TYPE_TO_BE_EDITED = "bookTypeToBeEdited";
	private static final String BOOK_ID = "bookId";
	private static final String USER_WELCOME_REQUEST = "ControllerServlet?action=WelcomeToAdd";
	private static final String UPDATED_RECORD_MESSAGE = "isUpdatedBook";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		AddBookDTO addBookDTO = new AddBookDTO();

		HttpSession session = request.getSession();
		ParameterSetter.setLanguage(request, session);
		ParameterSetter.setLanguage(request, session);
		ParameterSetter.storingTypeOFBookToBeEdited(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		ParameterSetter.setAction(request, session);
		boolean isUpdated = false;
		addBookDTO.setTitle(request.getParameter(TITLE));
		addBookDTO.setDescription(request.getParameter(DESCRIPTION));
		addBookDTO.setAuthor(request.getParameter(AUTHOR));
		addBookDTO.setPrice(Float.parseFloat(request.getParameter(PRICE)));
		addBookDTO.setBookProperty(request.getParameter(ITEM));
		addBookDTO.setTypeOfBook((String) session.getAttribute(TYPE_TO_BE_EDITED));
		addBookDTO.setBookId((String) session.getAttribute(BOOK_ID));

		addBookDTO.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			isUpdated = service.editBook(addBookDTO);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.toString());
			logger.log(Level.ERROR, "Exception occured", e);
		}

		session.setAttribute(UPDATED_RECORD_MESSAGE, isUpdated);

		return USER_WELCOME_REQUEST;
	}

}
