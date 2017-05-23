package com.epam.library.dao.builder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.builder.BookParser;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.builder.factory.BookParserBuilder;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Book;
import com.epam.library.domain.PaperBook;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;

public class AllBookParser implements BookParser {
	private final static String COUNT = "  SELECT  * from book b left join book_type on b_t_id=b_book_type";

	@Override
	public String returningQuery() {
		return " SELECT  * from book b LEFT join e_book_translator eb  ON b.b_e_book=eb.e_b_book  left join book_type on b_t_id=b_book_type LEFT join p_book_translator pb  ON b.b_p_book=pb.p_b_book LEFT join book_translator bt on bt.b_t_b_book=b.b_id  LEFT join app_language al  ON eb.e_b_app_language=al.a_l_code  where  (e_b_app_language=? or p_b_app_language=?) and (bt.b_t_app_language=?)  ";

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
			if (rs == null) {
				rs = returningResultStatement("en", preparedStatement, bookId);
			}
		} catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}

	@Override
	public List<?> findBookByCategory(Request request, ResultSet rs) throws BuilderException, DBManagerException {

		BookParserBuilder queryObject = BookParserBuilder.getInstance();

		BookParser query = null;
		List<Book> listOfBooks = null;
		List<List<Book>> allBooks = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User retrievedUser = new User();
		int count = 0;
		try {
			connection = DBManager.getConnectionFromPool();

			preparedStatement = connection.prepareStatement(COUNT);

			ResultSet rsCount = preparedStatement.executeQuery();
			while (rsCount.next()) {

				if (rsCount.getString("b_t_name").equals("EB")) {
					System.out.println("in eb");
					
					query = queryObject.getQuery("EB");

				} else
				{
					System.out.println("in pb");
					query = queryObject.getQuery("PB");
				}

				listOfBooks = (List<Book>) query.findBookByCategory(request, rs);
				allBooks.add( listOfBooks);
				for (Book tt : listOfBooks) {
					System.out.println("in fn" + tt.getTitle());
				}
			}
			for (Book tt : listOfBooks) {
				System.out.println(tt.getTitle());
			}

		} catch (SQLException e1) {
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


		return allBooks;
	}

	@Override
	public Book findBook(Request request, ResultSet rs,String bookId) throws BuilderException {
		BookParserBuilder queryObject = BookParserBuilder.getInstance();

		BookParser query = null;
	Book listOfBooks = null;
	List<Book> ll=new ArrayList();
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User retrievedUser = new User();
		int count = 0;
		try {
			try {
				connection = DBManager.getConnectionFromPool();
			} catch (DBManagerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			preparedStatement = connection.prepareStatement(COUNT);

			ResultSet rsCount = preparedStatement.executeQuery();
			while (rsCount.next()) {

				if (rsCount.getString("b_t_name").equals("EB")) {
					System.out.println("in eb");
					query = queryObject.getQuery("EB");

				} else
					query = queryObject.getQuery("PB");

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

	

	@Override
	public String returningPaperQuery() {
		return "select b_id from book ";
	}

	@Override
	public ResultSet returningRs(PreparedStatement preparedStatement) throws BuilderException {
		ResultSet rs = null;
		try
		{
			

			rs = preparedStatement.executeQuery();
		} 
		
		catch (SQLException ex) {

			throw new BuilderException("Database Connectivity Exception ", ex);
		}
		return rs;
	}
}

