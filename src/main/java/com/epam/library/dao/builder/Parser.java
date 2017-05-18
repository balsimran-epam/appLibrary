package com.epam.library.dao.builder;

import java.util.List;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Request;

public interface Parser {
	List<?> findBookByCategory(Request request) throws BuilderException;
}
