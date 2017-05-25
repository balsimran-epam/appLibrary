package com.epam.library.dao.builder.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.builder.SearchBookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.dao.impl.BookParamEnum;
import com.epam.library.domain.ElectronicBook;
import com.epam.library.domain.SearchBookDTO;

public class EBookSearchparser implements SearchBookParser {

	@Override
	public String returningQuery() {
	
		return "SELECT  * from book b left join book_type on b_book_type=b_t_id left join e_book_translator eb  ON b.b_e_book=eb.e_b_book  LEFT join book_translator bt on bt.b_t_b_book=b.b_id  LEFT join app_language al  ON (eb.e_b_app_language=al.a_l_code ) where  (e_b_app_language=? ) and (bt.b_t_app_language=?)  and b_t_title like ? and b_t_author like ? and b_t_description like ? and b_price between ? and ?";
	}

	@Override
	public ResultSet returningResultStatement(String language, PreparedStatement preparedStatement,
			SearchBookDTO searchedBook) throws BuilderException {
		ResultSet rs = null;
		try {
		
			preparedStatement.setString(1, language);
			preparedStatement.setString(2, language);
			preparedStatement.setString(3, "%" + searchedBook.getTitle() + "%");
			preparedStatement.setString(4, "%" + searchedBook.getAuthor() + "%");
			preparedStatement.setString(5, "%" + searchedBook.getDescription() + "%");
			preparedStatement.setString(6, searchedBook.getMinPrice());
			preparedStatement.setString(7, searchedBook.getMaxPrice());

			rs = preparedStatement.executeQuery();

		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}

	@Override
	public List<?> searchBook(SearchBookDTO request, ResultSet rs) throws BuilderException, DBManagerException {
		List<ElectronicBook> electronicBookList = new ArrayList<>();
		if (rs == null) {
			throw new BuilderException("Book  not found");

		}

		try {
			while (rs.next()) {

				ElectronicBook retrievedBook = new ElectronicBook();

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

}
