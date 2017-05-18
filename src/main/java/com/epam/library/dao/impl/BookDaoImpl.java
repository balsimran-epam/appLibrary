package com.epam.library.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.builder.Parser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.ParserBuilder;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class BookDaoImpl implements BookDAO{

	@Override
	public List<Book> findBookByCategory(Request request) throws DAOException {
		List<Book> allProduct = new ArrayList<>();
		ParserBuilder bookFactory = ParserBuilder.getInstance();
		System.out.println();
		Parser parser = bookFactory.getBook(request.getTypeOfBook());
		
		try {
			
			allProduct =  (List<Book>) parser.findBookByCategory(request);
			
		} catch (BuilderException be) {
			throw new DAOException(be);
		}
		
		return allProduct;
	}

}
