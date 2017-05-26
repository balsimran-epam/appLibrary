package com.epam.library.dao.builder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;

public interface BookParser {
	String returningQuery();
	ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, String bookId) throws BuilderException;
	List<?> findBookByCategory(DisplayBookDTO request,ResultSet rs) throws BuilderException, DBManagerException;
	Book findBook(DisplayBookDTO request,ResultSet rs, String bookId) throws BuilderException;
	
}
