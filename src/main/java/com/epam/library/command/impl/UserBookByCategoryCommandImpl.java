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
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserBookByCategoryCommandImpl implements Command {
	private static Logger logger = Logger.getLogger(UserBookByCategoryCommandImpl.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		List<Book> electronicBookList=new ArrayList<>();
		Request userRequested = new Request();
String view=null;
		userRequested.setLanguage((String) session.getAttribute("language"));
		userRequested.setTypeOfBook((String) request.getAttribute(BookEnum.TYPE_OF_BOOK.getParam()));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();

		try {
			electronicBookList=service.getBookList(userRequested);
		} catch (ServiceException e) {
			logger.log(Level.ERROR, "Exception occured", e);
		}
		
			request.setAttribute(LoginParamEnum.BOOK_INFO.getParam(), electronicBookList);
	
		request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
				LoginParamEnum.FORWARD_REQUEST.getParam());
		view = LoginParamEnum.USER_PAGE.getParam();
		return view;

	}
}
