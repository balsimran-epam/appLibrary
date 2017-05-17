package com.epam.library.dao.factory;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.impl.BookDaoImpl;
import com.epam.library.dao.impl.LoginDaoImpl;

public class DAOFactory {
	private final static DAOFactory daoFactory = new DAOFactory();
	private final static UserDAO userDao = new LoginDaoImpl();
	private final static BookDAO bookDao = new BookDaoImpl();
	public static DAOFactory getInstance() {
		return daoFactory;
	}

	public UserDAO getUserDao() {
		return userDao;

	}
	
	public BookDAO getBookDao() {
		return bookDao;

	}

}
