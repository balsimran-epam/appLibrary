package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class AddBookParser implements com.epam.library.dao.builder.AddBookParser{

	@Override
	public int returningResultStatement(AddBookDTO addBookDTO, PreparedStatement preparedStatement)
			throws BuilderException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String returningBookQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
