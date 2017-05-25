package com.epam.library.dao.builder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;
import com.epam.library.domain.SearchBookDTO;

public interface SearchBookParser {
	
	String returningQuery();
	ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, SearchBookDTO searchedBook) throws BuilderException;
	List<?> searchBook(SearchBookDTO searchedBook,ResultSet rs) throws BuilderException, DBManagerException;


}
