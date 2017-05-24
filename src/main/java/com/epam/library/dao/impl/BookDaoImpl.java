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
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.AddBookParserBuilder;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.builder.factory.EditBookParserBuilder;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class BookDaoImpl implements BookDAO {
	private static Logger logger = Logger.getLogger(BookDaoImpl.class);

	@Override
	public List<List<Book>> findBookByCategory(Request request) throws DAOException {

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
	public Book findBook(Request request) throws DAOException {
		Book allProduct = new Book();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		BookParserBuilder queryObject = BookParserBuilder.getInstance();
		System.out.println("type" + request.getTypeOfBook());
		BookParser query = queryObject.getQuery(request.getTypeOfBook());
		try {
			connection = DBManager.getConnectionFromPool();
			String sqlquery = query.returningQuery();
			preparedStatement = connection.prepareStatement(sqlquery);
			ResultSet rs = query.returningResultStatement(request.getLanguage(), preparedStatement,
					request.getBookId());
			allProduct = query.findBook(request, rs, request.getBookId());
			System.out.println("now" + allProduct);
			if (allProduct.getTitle()==null) {
				System.out.println("yes");

				rs = query.returningResultStatement("en", preparedStatement, request.getBookId());
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
		boolean isDataSaved = false;
		boolean isUserNameAvailable = false;
		int isInserted = 0;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			AddBookParserBuilder queryObject = AddBookParserBuilder.getInstance();
			System.out.println("type" + addBookDTO.getTypeOfBook());
			AddBookParser query = queryObject.getQuery(addBookDTO.getTypeOfBook());
			String sqlQuery = query.returningBookQuery();
			CallableStatement stmt = connection.prepareCall(sqlQuery);
			isInserted = query.returningResultStatement(addBookDTO, stmt);
			System.out.println(isInserted);

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
			System.out.println("type" + addBookDTO.getTypeOfBook());
			EditBookParser query = queryObject.getQuery(addBookDTO.getTypeOfBook());
			String sqlQuery = query.returningBookQuery();
			CallableStatement stmt = connection.prepareCall(sqlQuery);
			isInserted = query.returningResultStatement(addBookDTO, stmt);
			System.out.println(isInserted);

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
}
