package com.epam.library.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.exception.ValidatorException;
import com.epam.library.service.util.Validator;

public class BookServiceImpl implements BookService {

	@Override
	public List<Book> getBookList(Request request) throws ServiceException  {
		try {
			Validator.validatingSelectedBookType(request);
		} catch (ValidatorException ve) {
			throw new ServiceException(ve);
		}
		List<Book> electronicBookList=new ArrayList<>();
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			electronicBookList = bookDao.findBookByCategory(request);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}
		
		return electronicBookList;
	}
	}

