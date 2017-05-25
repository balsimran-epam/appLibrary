package com.epam.library.command.impl;

import java.util.ArrayList;
import java.util.List;

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

public class UserBookByCategoryCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UserBookByCategoryCommandImpl.class);
	private static final String IS_ALL_SELECTED = "isAll";
	private static final String ALL_SELECTED = "ALL";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		ParameterSetter.setAction(request, session);
	
		ParameterSetter.setTypeOfBook(request, session);
		ParameterSetter.setIRole(request, session);

		List<List<Book>> electronicBookList = new ArrayList<>();
		Request userRequested = new Request();

		userRequested.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook((String) session.getAttribute(FormParamEnum.TYPE_OF_BOOK.getParam()));
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

		if (session.getAttribute("user") != null) {

			return TargetPage.ADMIN_PAGE.getParam();
		} else
			return TargetPage.USER_PAGE.getParam();

	}
}
