package com.epam.library.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.LoginRequestMapping;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UserSelectedBookCommandImpl implements Command{
	private static final String BOOK_ID="bookId";
	private static final String ALL_BOOK="selected";
	private static Logger logger = Logger.getLogger(UserSelectedBookCommandImpl.class);
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
	
		LoginRequestMapping.setLanguage(request, session);
		LoginRequestMapping.setTypeOfBook(request, session);
		LoginRequestMapping.setIdOfSelectedBook(request, session);
		LoginRequestMapping.setAction(request, session);
		List<Book> electronicBookList=new ArrayList<>();
		Request userRequested = new Request();
String view=null;
		userRequested.setLanguage((String) session.getAttribute(LoginParamEnum.LANGUAGE.getParam()));
		userRequested.setTypeOfBook(ALL_BOOK);
		userRequested.setType((String) (String) session.getAttribute(LoginParamEnum.TYPE_OF_BOOK.getParam()));
		userRequested.setBookId(  (String) request.getAttribute(BOOK_ID));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();	
		try {
			electronicBookList=service.getBookList(userRequested);
		} catch (ServiceException e) {
			request.setAttribute("exceptionOccured", e.toString());
			logger.log(Level.ERROR, "Exception occured", e);
		}
		request.setAttribute("selectedBookInfo", electronicBookList);
		
		request.setAttribute(ParamEnum.REQUESTED_METHOD_TO_CALL.getParam(),
				LoginParamEnum.FORWARD_REQUEST.getParam());
		view = LoginParamEnum.BOOK_INFO_PAGE.getParam();
		return view;	
	}

}
