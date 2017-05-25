package com.epam.library.dao.builder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.Request;

public class SelectedBookParser implements BookParser {
	private final static String COUNT = "  SELECT  * from book b left join book_type on b_t_id=b_book_type  where b_id=?";
	private static final String PAPER_BOOK="PB";
	private static final String ELECTRONIC_BOOK="EB";
	private static final String BOOK_TYPE="b_t_name";
	@Override
	public String returningQuery() {
		
	
		return "SELECT  * from book b LEFT join e_book_translator eb  ON b.b_e_book=eb.e_b_book  LEFT join p_book_translator pb  ON b.b_p_book=pb.p_b_book LEFT join book_translator bt on bt.b_t_b_book=b.b_id  LEFT join app_language al  ON eb.e_b_app_language=al.a_l_code  where  (e_b_app_language=? or p_b_app_language=?) and (bt.b_t_app_language=?) and b_id=?";
	}

	@Override
	public ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, String bookId)
			throws BuilderException {
	
		ResultSet rs = null;
		try {

			preparedStatement.setString(1, language);
			preparedStatement.setString(2, language);
			preparedStatement.setString(3, language);
			preparedStatement.setString(4, bookId);

			rs = preparedStatement.executeQuery();
			
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}

	@Override
	public List<?> findBookByCategory(Request request, ResultSet rs) throws BuilderException {
		BookParserBuilder queryObject = BookParserBuilder.getInstance();
		BookParser query = queryObject.getQuery(request.getType());
		

		List<Book> listOfBooks = null;
		try {
			listOfBooks = (List<Book>) query.findBookByCategory(request, rs);
		} catch (BuilderException | DBManagerException e) {
			throw new BuilderException("Database Connectivity Exception ", e);
		}

		return listOfBooks;
	}

	@Override
	public Book findBook(Request request, ResultSet rs,String bookId) throws BuilderException {
		BookParserBuilder queryObject = BookParserBuilder.getInstance();
System.out.println("in fb");

		BookParser query = null;
	Book listOfBooks = null;
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			try {
				connection = DBManager.getConnectionFromPool();
			} catch (DBManagerException e) {
				throw new BuilderException("Database Connectivity Exception ", e);
			}
	
	
			preparedStatement = connection.prepareStatement(COUNT);
			preparedStatement.setString(1, bookId);
			ResultSet rsCount = preparedStatement.executeQuery();
			while (rsCount.next()) {

				if (rsCount.getString(BOOK_TYPE).equals(ELECTRONIC_BOOK)) {
				
					query = queryObject.getQuery(ELECTRONIC_BOOK);

				} else
				{
				
					query = queryObject.getQuery(PAPER_BOOK);
				}

				listOfBooks = query.findBook(request, rs,bookId);
				
				
			

		}} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		catch (BuilderException e) {
			throw new BuilderException("Database Connectivity Exception ", e);
		}finally {
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

