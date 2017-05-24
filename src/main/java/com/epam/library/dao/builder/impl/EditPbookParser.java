package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epam.library.dao.builder.EditBookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.domain.AddBookDTO;

public class EditPbookParser implements EditBookParser {
	private static final String EDIT_PAPER_QUERY = "{call editPbook(?,?,?,?,?,?,?,?,?)}";

	@Override
	public String returningBookQuery() {
		return EDIT_PAPER_QUERY;
	}

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
			preparedStatement.setString(8, addBookDTO.getBookId());
			preparedStatement.setString(9, addBookDTO.getLanguage());
			i = preparedStatement.executeUpdate();
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return i;
	}
}
