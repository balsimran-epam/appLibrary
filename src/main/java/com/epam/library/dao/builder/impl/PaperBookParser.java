package com.epam.library.dao.builder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.Parser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.dao.impl.BookParamEnum;
import com.epam.library.domain.PaperBook;
import com.epam.library.domain.Request;

public class PaperBookParser implements Parser {
	private static Logger logger = Logger.getLogger(PaperBookParser.class);
	private static final String FIND_ELECTRONIC_BOOK = "SELECT  *  from book b inner join p_book_translator pb  ON b.b_p_book=pb.p_b_book inner join book_translator bt on bt.b_t_b_book=b.b_id  inner join app_language al  ON pb.p_b_app_language=al.a_l_code  where bt.b_t_app_language=? and p_b_app_language=?";
	@Override
	public List<?> findBookByCategory(Request request) throws BuilderException{
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<PaperBook> electronicBookList=new ArrayList<>();


		try {

			connection = DBManager.createNewConnectionForPool();

			preparedStatement = connection.prepareStatement(FIND_ELECTRONIC_BOOK);

			preparedStatement.setString(1, request.getLanguage());
			preparedStatement.setString(2, request.getLanguage());
			ResultSet rs = preparedStatement.executeQuery();
			
			electronicBookList=dataSetForPaperBook(rs);
			
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		} catch (DBManagerException ex) {
			throw new BuilderException("Database Connectivity Exception ", ex);
		}

		finally {

			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Statement Exception", e);
			}
			try {
				DBManager.returnConnectionToPool(connection);
				DBManager.closeConnection(connection);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}
		}

		

		return electronicBookList;

	}
	public  List<PaperBook> dataSetForPaperBook(ResultSet rs) throws BuilderException 
	{
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
	}


