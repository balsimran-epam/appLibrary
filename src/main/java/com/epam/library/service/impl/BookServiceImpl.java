package com.epam.library.service.impl;

import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.SearchBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.exception.ValidatorException;
import com.epam.library.service.util.Validator;

public class BookServiceImpl implements BookService {

	@Override
	public List<List<Book>> getBookList(DisplayBookDTO request) throws ServiceException   {
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
	public Book getBook(DisplayBookDTO request) throws ServiceException {
		
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
			throw new ServiceException(e);
		}
		return (isBookAdded) ? true : false;
	}

	@Override
	public boolean editBook(AddBookDTO addBookDTO) throws ServiceException {
		boolean isBookAdded=false;
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			isBookAdded = bookDao.editBook(addBookDTO);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return (isBookAdded) ? true : false;
	}

	@Override
	public List<Book> searchBook(String language,SearchBookDTO searchedBook) throws ServiceException {
		try {
			Validator.validatingSelectedBookType(searchedBook);
		} catch (ValidatorException ve) {
			throw new ServiceException(ve);
		}
	List<Book> electronicBookList=null;
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		BookDAO bookDao = daoFactory.getBookDao();
		try {
			electronicBookList = bookDao.searchBook(language,searchedBook);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}
		
		return electronicBookList;
	}
	}

