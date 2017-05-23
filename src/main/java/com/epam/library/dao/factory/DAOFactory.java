package com.epam.library.dao.factory;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.LoginDAO;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.LoginDaoImpl;
import com.epam.library.dao.impl.UserDaoImpl;

public class DAOFactory {
	private final static DAOFactory daoFactory = new DAOFactory();
	private final static LoginDAO loginDao = new LoginDaoImpl();
	private final static BookDAO bookDao = new BookDaoImpl();
	private final static UserDAO userDao = new UserDaoImpl();
	public static DAOFactory getInstance() {
		return daoFactory;
	}

	public LoginDAO getLoginDao() {
		return loginDao;

	}
	
	public BookDAO getBookDao() {
		return bookDao;

	}
	
	public UserDAO getUserDao() {
		return userDao;

	}
}
