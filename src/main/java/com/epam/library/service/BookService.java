package com.epam.library.service;

import java.util.List;

import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.service.exception.ServiceException;

public interface BookService {

	List<Book> getBookList(Request userRequested) throws ServiceException ;

}
