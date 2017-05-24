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

public class AddBookCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(AddBookCommandImpl.class);
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String AUTHOR = "author";
	private static final String PRICE = "price";
	private static final String ITEM = "item";
	private static final String TYPE_TO_BE_ADDED = "bookTypeToBeAdded";
	private static final String USER_WELCOME_REQUEST = "ControllerServlet?action=WelcomeToAdd";
	private static final String INSERTED_RECORD_MESSAGE = "inserted";

	public String execute(HttpServletRequest request, HttpServletResponse response) {
		AddBookDTO addBookDTO = new AddBookDTO();
		HttpSession session = request.getSession();
		boolean isAdded = false;
		ParameterSetter.setAction(request, session);
		addBookDTO.setTitle(request.getParameter(TITLE));
		addBookDTO.setDescription(request.getParameter(DESCRIPTION));
		addBookDTO.setAuthor(request.getParameter(AUTHOR));
		addBookDTO.setPrice(Float.parseFloat(request.getParameter(PRICE)));
		addBookDTO.setBookProperty(request.getParameter(ITEM));
		addBookDTO.setTypeOfBook((String) session.getAttribute(TYPE_TO_BE_ADDED));
		System.out.println(request.getParameter(ITEM));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			isAdded = service.addBook(addBookDTO);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.getMessage());
			logger.log(Level.ERROR, "Exception", e);
		}
		session.setAttribute(INSERTED_RECORD_MESSAGE, isAdded);
		return USER_WELCOME_REQUEST;
	}

}
