package com.epam.library.dao;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.SearchBookDTO;

public interface BookDAO {
	

	List<List<Book>> findBookByCategory(DisplayBookDTO request) throws DAOException ;
	Book findBook(DisplayBookDTO request) throws DAOException ;
	boolean addBook(AddBookDTO addBookDTO) throws DAOException;
	boolean editBook(AddBookDTO addBookDTO) throws DAOException;
	List<Book> searchBook(String language,SearchBookDTO searchedBook) throws DAOException;



	
}
