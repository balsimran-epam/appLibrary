package com.epam.library.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.dao.BookDAO;
import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.AddBookParser;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.EditBookParser;
import com.epam.library.dao.builder.SearchBookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.AddBookParserBuilder;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.builder.factory.EditBookParserBuilder;
import com.epam.library.dao.builder.factory.SearchBookParserBuilder;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.SearchBookDTO;

public class BookDaoImpl implements BookDAO {
	private static Logger logger = Logger.getLogger(BookDaoImpl.class);
	private static final String DEFAULT_LANGUAGE = "en";

	@SuppressWarnings("unchecked")
	@Override
	public List<List<Book>> findBookByCategory(DisplayBookDTO request) throws DAOException {

		List<List<Book>> allProduct = new ArrayList<>();

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
			allProduct = (List<List<Book>>) query.findBookByCategory(request, rs);
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

	@Override
	public Book findBook(DisplayBookDTO request) throws DAOException {
		Book allProduct = null;
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
			allProduct = query.findBook(request, rs, request.getBookId());
			if (allProduct == null) {

				rs = query.returningResultStatement(DEFAULT_LANGUAGE, preparedStatement, request.getBookId());
				allProduct = query.findBook(request, rs, request.getBookId());

			}
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

	@Override
	public boolean addBook(AddBookDTO addBookDTO) throws DAOException {
		Connection connection = null;
		int isInserted = 0;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			AddBookParserBuilder queryObject = AddBookParserBuilder.getInstance();
			AddBookParser query = queryObject.getQuery(addBookDTO.getTypeOfBook());
			String sqlQuery = query.returningBookQuery();
			CallableStatement stmt = connection.prepareCall(sqlQuery);
			isInserted = query.returningResultStatement(addBookDTO, stmt);

			connection.commit();
		} catch (SQLException | DBManagerException | BuilderException se) {

			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException ex) {

				throw new DAOException("Database Connectivity Exception ", ex);
			}
			throw new DAOException("Issue with DB parameters while " + "inserting user.", se);
		} finally {

			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}

			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DAOException("Issue with DB parameters while " + "setting auto commit.", e);
			}

		}
		return (isInserted != 0);
	}

	@Override
	public boolean editBook(AddBookDTO addBookDTO) throws DAOException {
		Connection connection = null;

		int isInserted = 0;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			EditBookParserBuilder queryObject = EditBookParserBuilder.getInstance();

			EditBookParser query = queryObject.getQuery(addBookDTO.getTypeOfBook());
			String sqlQuery = query.returningBookQuery();
			CallableStatement stmt = connection.prepareCall(sqlQuery);
			isInserted = query.returningResultStatement(addBookDTO, stmt);

			connection.commit();
		} catch (SQLException | DBManagerException | BuilderException se) {

			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException ex) {

				throw new DAOException("Database Connectivity Exception ", ex);
			}
			throw new DAOException("Issue with DB parameters while " + "inserting user.", se);
		} finally {

			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}

			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DAOException("Issue with DB parameters while " + "setting auto commit.", e);
			}

		}
		return (isInserted != 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBook(String language, SearchBookDTO searchedBook) throws DAOException {
		List<Book> allProduct = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		SearchBookParserBuilder queryObject = SearchBookParserBuilder.getInstance();
		SearchBookParser query = queryObject.getQuery(searchedBook.getTypeOfBook());

		try {
			connection = DBManager.getConnectionFromPool();
			String sqlquery = query.returningQuery();
			preparedStatement = connection.prepareStatement(sqlquery);
			ResultSet rs = query.returningResultStatement(language, preparedStatement, searchedBook);
			allProduct = (List<Book>) query.searchBook(searchedBook, rs);
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
