package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserSelectedBookCommandImpl implements Command {

	private static final String BOOK_ID = "bookId";
	private static final String ALL_BOOK = "selected";
	private static final String SELECTED_BOOK_INFO = "selectedBookInfo";
	private static Logger logger = Logger.getLogger(UserSelectedBookCommandImpl.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		
		HttpSession session = request.getSession();

		ParameterSetter.setLanguage(request, session);
		ParameterSetter.setTypeOfBook(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		ParameterSetter.setAction(request, session);
		Book electronicBookList = new Book();
		Request userRequested = new Request();
		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) (String) session.getAttribute(FormParamEnum.TYPE_OF_BOOK.getParam()));
		if (userRequested.getType().equals("ALL")) {
			System.out.println("yes set");
			request.setAttribute("isAll", "true");
			session.getAttribute("isAll");
		}

		userRequested.setBookId((String) request.getAttribute(BOOK_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		electronicBookList = service.getBook(userRequested);

		System.out.println("pp" + electronicBookList.getTitle());

		request.setAttribute(SELECTED_BOOK_INFO, electronicBookList);
		return TargetPage.BOOK_INFO_PAGE.getParam();

	}

}
