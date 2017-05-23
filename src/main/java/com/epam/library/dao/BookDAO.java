package com.epam.library.dao;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public interface BookDAO {
	

	List<List<Book>> findBookByCategory(Request request) throws DAOException ;
	Book findBook(Request request) throws DAOException ;
	boolean addBook(AddBookDTO addBookDTO) throws DAOException;
	boolean editBook(AddBookDTO addBookDTO) throws DAOException;


	
}
