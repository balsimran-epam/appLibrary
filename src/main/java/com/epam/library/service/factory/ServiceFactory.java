package com.epam.library.service.factory;

import com.epam.library.service.BookService;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.LoginServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory serviceFactory = new ServiceFactory();
	private final static UserService userService = new LoginServiceImpl();
	private final static BookService bookService = new BookServiceImpl();

	private ServiceFactory() {

	}

	public static ServiceFactory getInstance() {
		return serviceFactory;
	}

	public UserService getUserService() {
		return userService;

	}

	public BookService getBookService() {
		return bookService;

	}	
	
}
