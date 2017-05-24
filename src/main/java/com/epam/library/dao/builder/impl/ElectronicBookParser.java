package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.impl.BookParamEnum;
import com.epam.library.domain.Book;
import com.epam.library.domain.ElectronicBook;
import com.epam.library.domain.Request;

public class ElectronicBookParser implements BookParser {

	
	
	@Override
	public String returningPaperQuery() {
		return "select b_id from book where b_book_type=?";
		/*return "SELECT  * from book b inner join e_book_translator eb   ON b.b_e_book=eb.e_b_book  inner join book_translator bt on bt.b_t_b_book=b.b_id  inner join app_language al  ON eb.e_b_app_language=al.a_l_code  where bt.b_t_app_language=? and e_b_app_language=?";*/

	}
	public String returningQuery() {
		
		return "SELECT  * from book b inner join e_book_translator eb   ON b.b_e_book=eb.e_b_book  inner join book_translator bt on bt.b_t_b_book=b.b_id  inner join app_language al  ON eb.e_b_app_language=al.a_l_code  where bt.b_t_app_language=? and e_b_app_language=? ";

	}
	@Override
	public ResultSet returningRs(PreparedStatement preparedStatement)
			throws BuilderException {

		ResultSet rs = null;
		try
		{
			preparedStatement.setInt(1, 1);
			rs = preparedStatement.executeQuery();
		} 
		
		catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}

		/*try {
			preparedStatement.setString(1, language);
			preparedStatement.setString(2, language);
			rs = preparedStatement.executeQuery();
			if (rs == null) {
				rs = returningResultStatement("en", preparedStatement, bookId);
			}
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}*/
		return rs;
	}

	
	public ResultSet returningResultStatement(String language, PreparedStatement preparedStatement, String bookId)
			throws BuilderException {

		ResultSet rs = null;
		

		try {
			preparedStatement.setString(1, language);
			preparedStatement.setString(2, language);
		
			rs = preparedStatement.executeQuery();
			if (rs == null) {
				System.out.println("null");
				rs = returningResultStatement("en", preparedStatement, bookId);
			}
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}
	@Override
	public List<?> findBookByCategory(Request request, ResultSet rs) throws BuilderException {
		
		List<ElectronicBook> electronicBookList = new ArrayList<>();
		if (rs == null) {
			throw new BuilderException("Book  not found");

		}

		try {
			while (rs.next()) {
				System.out.println("in electronic");
				
				ElectronicBook retrievedBook = new ElectronicBook();
				retrievedBook.setHoldType("EB");
				retrievedBook.setBookId(rs.getInt(BookParamEnum.BOOK_ID.getParam()));
				retrievedBook.setTitle(rs.getString(BookParamEnum.BOOK_TITLE.getParam()));
				retrievedBook.setAuthor(rs.getString(BookParamEnum.BOOK_AUTHOR.getParam()));
				retrievedBook.setDescription(rs.getString(BookParamEnum.BOOK_DESCRIPTION.getParam()));
				retrievedBook.setPrice(rs.getFloat(BookParamEnum.BOOK_PRICE.getParam()));
				retrievedBook.setQuantity(rs.getInt(BookParamEnum.BOOK_QUANTITY.getParam()));
				retrievedBook.setVersion(rs.getString(BookParamEnum.BOOK_VERSION.getParam()));
				electronicBookList.add(retrievedBook);

			}

		} catch (SQLException e) {
			throw new BuilderException("Problem in retrieving data Exception ", e);
		}
		return electronicBookList;
	}

	@Override
	public Book findBook(Request request, ResultSet rs, String bookId) throws BuilderException {
		System.out.println("in electronic");
		ElectronicBook retrievedBook = new ElectronicBook();
		if (rs == null) {
			throw new BuilderException("Book  not found");

		}

		try {
			while (rs.next()) {
				System.out.println("in electronic");
				retrievedBook.setHoldType("EB");
				retrievedBook.setBookId(rs.getInt(BookParamEnum.BOOK_ID.getParam()));
				retrievedBook.setTitle(rs.getString(BookParamEnum.BOOK_TITLE.getParam()));
				retrievedBook.setAuthor(rs.getString(BookParamEnum.BOOK_AUTHOR.getParam()));
				retrievedBook.setDescription(rs.getString(BookParamEnum.BOOK_DESCRIPTION.getParam()));
				retrievedBook.setPrice(rs.getFloat(BookParamEnum.BOOK_PRICE.getParam()));
				retrievedBook.setQuantity(rs.getInt(BookParamEnum.BOOK_QUANTITY.getParam()));
				retrievedBook.setVersion(rs.getString(BookParamEnum.BOOK_VERSION.getParam()));
				System.out.println("oo" + retrievedBook.getHoldType());

			}
			System.out.println(retrievedBook.getTitle());

		} catch (SQLException e) {
			throw new BuilderException("Problem in retrieving data Exception ", e);
		}
		return retrievedBook;
	}
}
