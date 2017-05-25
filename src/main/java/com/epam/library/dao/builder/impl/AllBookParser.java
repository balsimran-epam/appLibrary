package com.epam.library.dao.builder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class AllBookParser implements BookParser {
	private final static String COUNT = "  SELECT  * from book b left join book_type on b_t_id=b_book_type";
	private static final String All_BOOK_QUERY = "SELECT  * from book b LEFT join e_book_translator eb  ON b.b_e_book=eb.e_b_book  left join book_type on b_t_id=b_book_type LEFT join p_book_translator pb  ON b.b_p_book=pb.p_b_book LEFT join book_translator bt on bt.b_t_b_book=b.b_id  LEFT join app_language al  ON eb.e_b_app_language=al.a_l_code  where  (e_b_app_language=? or p_b_app_language=?) and (bt.b_t_app_language=?)  ";
	private static final String BOOK_TYPE = "b_t_name";
	private static final String ELECTRONIC_BOOK = "EB";
	private static final String PAPER_BOOK = "PB";

	@Override
	public String returningQuery() {
		return All_BOOK_QUERY;

	}

	@Override
	public ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, String bookId)
			throws BuilderException {

		ResultSet rs = null;

		try {
			preparedStatement.setString(1, language);
			preparedStatement.setString(2, language);
			preparedStatement.setString(3, language);

			rs = preparedStatement.executeQuery();

		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}

	@Override
	public List<?> findBookByCategory(Request request, ResultSet rs) throws BuilderException {

		BookParserBuilder queryObject = BookParserBuilder.getInstance();

		BookParser query = null;
		List<Book> listOfBooks = null;
		List<List<Book>> allBooks = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBManager.getConnectionFromPool();

			preparedStatement = connection.prepareStatement(COUNT);

			ResultSet rsCount = preparedStatement.executeQuery();
			while (rsCount.next()) {

				if (rsCount.getString(BOOK_TYPE).equals(ELECTRONIC_BOOK)) {

					query = queryObject.getQuery(ELECTRONIC_BOOK);

				} else {

					query = queryObject.getQuery(PAPER_BOOK);
				}

				listOfBooks = (List<Book>) query.findBookByCategory(request, rs);
				allBooks.add(listOfBooks);
				for (Book tt : listOfBooks) {
					System.out.println("in fn" + tt.getTitle());
				}
			}
			for (Book tt : listOfBooks) {
				System.out.println(tt.getTitle());
			}

		} catch (SQLException | BuilderException | DBManagerException ex) {
			throw new BuilderException("Database Connectivity Exception ", ex);
		} finally {
			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {

			}
			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {

			}
		}

		return allBooks;
	}

	@Override
	public Book findBook(Request request, ResultSet rs, String bookId) throws BuilderException {
		BookParserBuilder queryObject = BookParserBuilder.getInstance();

		BookParser query = null;
		Book listOfBooks = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DBManager.getConnectionFromPool();

			preparedStatement = connection.prepareStatement(COUNT);

			ResultSet rsCount = preparedStatement.executeQuery();
			while (rsCount.next()) {

				if (rsCount.getString(BOOK_TYPE).equals(ELECTRONIC_BOOK)) {

					query = queryObject.getQuery(ELECTRONIC_BOOK);

				} else
					query = queryObject.getQuery(PAPER_BOOK);

				listOfBooks = query.findBook(request, rs, bookId);

			}
		}

		catch (BuilderException | DBManagerException | SQLException e) {
			throw new BuilderException("Database Connectivity Exception ", e);
		} finally {
			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {

			}
			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {

			}
		}

		return listOfBooks;
	}

}
