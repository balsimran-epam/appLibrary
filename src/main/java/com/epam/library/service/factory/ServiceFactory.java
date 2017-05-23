package com.epam.library.service.factory;

import com.epam.library.service.BookService;
import com.epam.library.service.LoginService;
import com.epam.library.service.UserService;
import com.epam.library.service.impl.BookServiceImpl;
import com.epam.library.service.impl.LoginServiceImpl;
import com.epam.library.service.impl.UserServiceImpl;

public class ServiceFactory {
	private final static ServiceFactory serviceFactory = new ServiceFactory();
	private final static LoginService loginService = new LoginServiceImpl();
	private final static BookService bookService = new BookServiceImpl();
	private final static UserService userService = new UserServiceImpl();
	private ServiceFactory() {

	}

	public static ServiceFactory getInstance() {
		return serviceFactory;
	}

	public LoginService getLoginService() {
		return loginService;

	}

	public BookService getBookService() {
		return bookService;

	}	
	
	public UserService getUserService(){
		return userService;

	}	
	
	
}
