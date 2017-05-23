package com.epam.library.service.impl;

import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.exception.ValidatorException;
import com.epam.library.service.util.Validator;

public class BookServiceImpl implements BookService {

	@Override
	public List<List<Book>> getBookList(Request request) throws ServiceException   {
		try {
			Validator.validatingSelectedBookType(request);
		} catch (ValidatorException ve) {
			throw new ServiceException(ve);
		}
		List<List<Book>> electronicBookList=null;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			electronicBookList = bookDao.findBookByCategory(request);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}
		
		return electronicBookList;
	}

	@Override
	public Book getBook(Request request) throws ServiceException {
		System.out.println("in service impl");
		try {
			Validator.validatingSelectedBookType(request);
		} catch (ValidatorException ve) {
			throw new ServiceException(ve);
		}
		Book book=null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			book = bookDao.findBook(request);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}
		
		return book;
	}

	@Override
	public boolean addBook(AddBookDTO addBookDTO) throws ServiceException {
		boolean isBookAdded=false;
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			isBookAdded = bookDao.addBook(addBookDTO);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (isBookAdded) ? true : false;
	}

	@Override
	public boolean editBook(AddBookDTO addBookDTO) {
		boolean isBookAdded=false;
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			isBookAdded = bookDao.editBook(addBookDTO);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (isBookAdded) ? true : false;
	}
	}

