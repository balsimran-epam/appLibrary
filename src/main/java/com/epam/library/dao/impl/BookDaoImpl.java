package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class BookDaoImpl implements BookDAO {

	@Override
	public List<Book> findBookByCategory(Request request) throws DAOException {

		List<Book> allProduct = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookParserBuilder queryObject = BookParserBuilder.getInstance();
		BookParser query = queryObject.getQuery(request.getTypeOfBook());
		try {
			connection = DBManager.getConnectionFromPool();
			String sqlquery = query.returningQuery();
			preparedStatement = connection.prepareStatement(sqlquery);
			ResultSet rs = query.returningResultStatement(request.getLanguage(), preparedStatement,
					request.getBookId());
			allProduct = (List<Book>) query.findBookByCategory(request, rs);
		} catch (BuilderException | DBManagerException | SQLException be) {
			throw new DAOException(be);
		}

		finally {
			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {

			}
			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {

			}
		}

		return allProduct;
	}

}
