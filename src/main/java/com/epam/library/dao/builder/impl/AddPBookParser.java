package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.library.dao.builder.AddBookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.domain.AddBookDTO;

public class AddPBookParser implements AddBookParser {
	private static final String EDIT_PAPER_BOOK = "{call inserPBook(?,?,?,?,?,?,?)}";

	@Override
	public int returningResultStatement(AddBookDTO addBookDTO, PreparedStatement preparedStatement)
			throws BuilderException {
		int i = 0;
		try {

			preparedStatement.setInt(1, addBookDTO.getQuantity());
			preparedStatement.setFloat(2, addBookDTO.getPrice());
			preparedStatement.setInt(3, 2);

			preparedStatement.setString(4, addBookDTO.getBookProperty());
			preparedStatement.setString(5, addBookDTO.getTitle());
			preparedStatement.setString(6, addBookDTO.getDescription());
			preparedStatement.setString(7, addBookDTO.getAuthor());
			i = preparedStatement.executeUpdate();
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return i;
	}

	@Override
	public String returningBookQuery() {
		return EDIT_PAPER_BOOK;
	}

}
