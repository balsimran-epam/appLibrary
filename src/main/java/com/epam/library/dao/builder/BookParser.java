package com.epam.library.dao.builder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.domain.Request;

public interface BookParser {
	String returningQuery();
	ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, String bookId) throws BuilderException;
	List<?> findBookByCategory(Request request,ResultSet rs) throws BuilderException;
	
}