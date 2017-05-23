package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.library.dao.builder.AddBookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class AddPBookParser implements AddBookParser{

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
		return "{call inserPBook(?,?,?,?,?,?,?)}";
	}


}
