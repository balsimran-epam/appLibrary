package com.epam.library.dao.builder;

import java.sql.PreparedStatement;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.domain.AddBookDTO;

public interface EditBookParser {


	int returningResultStatement(AddBookDTO addBookDTO, PreparedStatement preparedStatement) throws BuilderException;
	
	String returningBookQuery();
}
