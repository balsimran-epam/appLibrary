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
import com.epam.library.domain.Request;

public class SelectedBookParser implements Parser{
	private static Logger logger = Logger.getLogger(ElectronicBookParser.class);
	private static final String FIND_ELECTRONIC_BOOK = "SELECT  * from book b LEFT join e_book_translator eb  ON b.b_e_book=eb.e_b_book  LEFT join p_book_translator pb  ON b.b_p_book=pb.p_b_book LEFT join book_translator bt on bt.b_t_b_book=b.b_id  LEFT join app_language al  ON eb.e_b_app_language=al.a_l_code  where  (e_b_app_language=? or p_b_app_language=?) and (bt.b_t_app_language=? or p_b_app_language=?) and b_id=?";
	@Override
	public List<?> findBookByCategory(Request request) throws BuilderException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<?> electronicBookList=new ArrayList<>();
	


		try {

			connection = DBManager.createNewConnectionForPool();

			preparedStatement = connection.prepareStatement(FIND_ELECTRONIC_BOOK);

			preparedStatement.setString(1, request.getLanguage());
			preparedStatement.setString(2, request.getLanguage());
			preparedStatement.setString(3, request.getLanguage());
			preparedStatement.setString(4, request.getLanguage());
			preparedStatement.setString(5, request.getBookId());
			
			ResultSet rs = preparedStatement.executeQuery();
		
			if(request.getType().equals("PB"))
			{
				
				PaperBookParser pb=new PaperBookParser();
				electronicBookList=pb.dataSetForPaperBook(rs);
			}
			else
			{
				
				ElectronicBookParser eb=new ElectronicBookParser();
				electronicBookList=eb.dataSet(rs);
			}
	

			
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

	}


