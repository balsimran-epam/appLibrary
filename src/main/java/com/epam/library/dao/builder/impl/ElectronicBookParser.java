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
import com.epam.library.dao.build.Parser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.dao.impl.BookParamEnum;
import com.epam.library.domain.ElectronicBook;
import com.epam.library.domain.Request;

public class ElectronicBookParser implements Parser{
	private static Logger logger = Logger.getLogger(ElectronicBookParser.class);
	private static final String FIND_ELECTRONIC_BOOK = "SELECT  * from book b inner join e_book_translator eb   ON b.b_e_book=eb.e_b_book  inner join book_translator bt on bt.b_t_b_book=b.b_id  inner join app_language al  ON eb.e_b_app_language=al.a_l_code  where bt.b_t_app_language=? and e_b_app_language=?";

	@Override
	public List<ElectronicBook> findBookByCategory(Request request) throws BuilderException {
				Connection connection = null;
			PreparedStatement preparedStatement = null;
			List<ElectronicBook> electronicBookList=new ArrayList<>();
	

			try {

				connection = DBManager.createNewConnectionForPool();

				preparedStatement = connection.prepareStatement(FIND_ELECTRONIC_BOOK);

				preparedStatement.setString(1, request.getLanguage());
				preparedStatement.setString(2, request.getLanguage());
				ResultSet rs = preparedStatement.executeQuery();
				electronicBookList=dataSet(rs);
			
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
	
	public List<ElectronicBook> dataSet(ResultSet rs) throws BuilderException 
	{
		List<ElectronicBook> electronicBookList=new ArrayList<>();
		if (rs == null) {
			throw new BuilderException("Book  not found");

		}

		try {
			while (rs.next()) {
				ElectronicBook retrievedBook=new ElectronicBook();
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
	
	

