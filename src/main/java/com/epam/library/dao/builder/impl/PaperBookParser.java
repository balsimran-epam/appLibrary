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
import com.epam.library.domain.PaperBook;
import com.epam.library.domain.DisplayBookDTO;

public class PaperBookParser implements BookParser{
	private static final String PAPER_BOOK_FIND = "SELECT  *  from book b inner join p_book_translator pb  ON b.b_p_book=pb.p_b_book inner join book_translator bt on bt.b_t_b_book=b.b_id  inner join app_language al  ON pb.p_b_app_language=al.a_l_code  where bt.b_t_app_language=? and p_b_app_language=? ";

	@Override
	public String returningQuery() {
		return PAPER_BOOK_FIND;
	}

	@Override
	public ResultSet returningResultStatement(String language, PreparedStatement preparedStatement,String bookId) throws BuilderException {
		ResultSet rs=null;
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
	public List<?> findBookByCategory(DisplayBookDTO request, ResultSet rs) throws BuilderException {
		List<PaperBook> electronicBookList=new ArrayList<>();
	
		try
		{
		if (rs == null) {
			throw new BuilderException("Book  not found");

		}

		while (rs.next()) {
			
			PaperBook retrievedBook=new PaperBook();
			retrievedBook.setBookId(rs.getInt(BookParamEnum.BOOK_ID.getParam()));
			retrievedBook.setTitle(rs.getString(BookParamEnum.BOOK_TITLE.getParam()));
			retrievedBook.setAuthor(rs.getString(BookParamEnum.BOOK_AUTHOR.getParam()));
			retrievedBook.setDescription(rs.getString(BookParamEnum.BOOK_DESCRIPTION.getParam()));
			retrievedBook.setPrice(rs.getFloat(BookParamEnum.BOOK_PRICE.getParam()));
			retrievedBook.setQuantity(rs.getInt(BookParamEnum.BOOK_QUANTITY.getParam()));
		
			retrievedBook.setTypeOfCover(rs.getString(BookParamEnum.TYPE_OF_COVER.getParam()));
		 electronicBookList.add(retrievedBook);

			}
		} catch (SQLException e) {
			throw new BuilderException("Problem in retrieving data Exception ", e);
		}
		return electronicBookList;
	}

	@Override
	public Book findBook(DisplayBookDTO request, ResultSet rs,String bookId) throws BuilderException {
		PaperBook retrievedBook = null;
		try
		{
		if (rs == null) {
			
			throw new BuilderException("Book  not found");

		}

		while (rs.next()) {
			retrievedBook = new PaperBook();
			retrievedBook.setBookId(rs.getInt(BookParamEnum.BOOK_ID.getParam()));
			retrievedBook.setTitle(rs.getString(BookParamEnum.BOOK_TITLE.getParam()));
			retrievedBook.setAuthor(rs.getString(BookParamEnum.BOOK_AUTHOR.getParam()));
			retrievedBook.setDescription(rs.getString(BookParamEnum.BOOK_DESCRIPTION.getParam()));
			retrievedBook.setPrice(rs.getFloat(BookParamEnum.BOOK_PRICE.getParam()));
			retrievedBook.setQuantity(rs.getInt(BookParamEnum.BOOK_QUANTITY.getParam()));
			retrievedBook.setTypeOfCover(rs.getString(BookParamEnum.TYPE_OF_COVER.getParam()));
		

			}
		} catch (SQLException e) {
			throw new BuilderException("Problem in retrieving data Exception ", e);
		}
		return retrievedBook;
	}



	
	}


