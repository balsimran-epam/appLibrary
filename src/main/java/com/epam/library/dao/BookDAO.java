package com.epam.library.dao;

import java.util.List;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public interface BookDAO {
	

	List<Book> findBookByCategory(Request request) throws DAOException;


	
}
