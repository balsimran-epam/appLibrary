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
import com.epam.library.domain.SearchBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class SearchBookCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(SearchBookCommandImpl.class);
	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String AUTHOR = "author";
	private static final String MIN_PRICE = "minPrice";
	private static final String MAX_PRICE = "maxPrice";
	private static final String ITEM = "item";
	private static final String DEFAULT_MIN_PRICE = "10";
	private static final String DEFAULT_MAX_PRICE = "100";
	private static final String DEFAULT_SEARCH_TYPE = "PB";
	private static final String IS_EMPTY = "emptySearch";
	private static final String TRUE_FLAG = "true";
	private static final String ACTION = "action";
	private static final String IS_USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String actionName = (String) request.getParameter(ACTION);
		if (actionName != null && !actionName.isEmpty()) {

			session.setAttribute(ACTION, request.getParameter(ACTION));
		}

		String role = (String) request.getParameter(IS_USER);

		if (role != null && !role.isEmpty()) {

			session.setAttribute(IS_USER, request.getParameter(IS_USER));
		}

		List<Book> bookList = new ArrayList<>();
		SearchBookDTO searchedBook = new SearchBookDTO();

		String language = (String) session.getAttribute(FormParamEnum.LANGUAGE.getParam());
		searchedBook.setTypeOfBook((String) request.getParameter(ITEM));
		searchedBook.setTitle(request.getParameter(TITLE));
		searchedBook.setDescription(request.getParameter(DESCRIPTION));
		searchedBook.setMinPrice(request.getParameter(MIN_PRICE));
		searchedBook.setMaxPrice(request.getParameter(MAX_PRICE));
		searchedBook.setAuthor(request.getParameter(AUTHOR));
		if (searchedBook.getTypeOfBook() == null) {

			searchedBook.setTypeOfBook(DEFAULT_SEARCH_TYPE);
		}

		if ((searchedBook.getMinPrice() == "") || (searchedBook.getMaxPrice() == "")) {
			searchedBook.setMinPrice(DEFAULT_MIN_PRICE);
			searchedBook.setMaxPrice(DEFAULT_MAX_PRICE);

		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		try {
			bookList = service.searchBook(language, searchedBook);
		} catch (ServiceException e) {
			request.setAttribute(FormParamEnum.EXCEPTION_CAUGHT.getParam(), e.toString());
			logger.log(Level.ERROR, "Exception occured", e);
		}

		request.setAttribute(FormParamEnum.BOOK_INFO.getParam(), bookList);
		request.setAttribute(IS_EMPTY, TRUE_FLAG);

		if (session.getAttribute(IS_USER) != null) {

			return TargetPage.ADMIN_PAGE.getParam();
		} else
			return TargetPage.USER_PAGE.getParam();

	}
}
