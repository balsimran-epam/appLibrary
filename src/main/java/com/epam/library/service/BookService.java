package com.epam.library.service;

import java.util.List;

import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.SearchBookDTO;
import com.epam.library.service.exception.ServiceException;

public interface BookService {

	List<List<Book>> getBookList(DisplayBookDTO userRequested) throws ServiceException ;
	Book getBook(DisplayBookDTO userRequested) throws ServiceException;
	boolean addBook(AddBookDTO addBookDTO) throws ServiceException;
	boolean editBook(AddBookDTO addBookDTO) throws ServiceException;
	List<Book> searchBook(String language, SearchBookDTO searchedBook) throws ServiceException;

}
