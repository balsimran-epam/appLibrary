package com.epam.library.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.UserDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;

public class LoginDaoImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(LoginDaoImpl.class);
	private static final String SELECT_USER_SQL = "select * from user INNER join user_role ON u_r_id=u_user_role INNER JOIN user_translator on u_t_user=u_id where u_t_app_language=? and u_user_name=? and u_password=?";
	private static final String USER_ID = "u_id";
	private static final String USER_NAME = "u_user_name";
	private static final String USER_PASSWORD = "u_password";
	private static final String USER_ROLE = "u_r_role_name";
	private static final String NAME = "u_t_name";

	public User getUserData(Request user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User retrievedUser = new User();

		try {

			connection = DBManager.createNewConnectionForPool();

			preparedStatement = connection.prepareStatement(SELECT_USER_SQL);
			preparedStatement.setString(1, user.getLanguage());
			preparedStatement.setString(2, user.getUserName());
			preparedStatement.setString(3, user.getPassword());

			ResultSet rs = preparedStatement.executeQuery();
			if (rs==null) {
				throw new DAOException("User  not found");

			}
			while (rs.next()) {

				retrievedUser.setUserId(rs.getInt(USER_ID));
				retrievedUser.setUserName(rs.getString(USER_NAME));
				retrievedUser.setPassword(rs.getString(USER_PASSWORD));
				retrievedUser.setUserRole(rs.getString(USER_ROLE));
				retrievedUser.setName(rs.getString(NAME));

			}
			

		} catch (SQLException ex) {

			throw new DAOException("Database Connectivity Exception ", ex);
		} catch (DBManagerException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
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

		return retrievedUser;

	}

}
