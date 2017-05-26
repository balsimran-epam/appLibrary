package com.epam.library.dao.impl;

import java.sql.CallableStatement;
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
import com.epam.library.domain.EditUserDTO;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.domain.User;

public class UserDaoImpl implements UserDAO {
	private static final String CALL_INSERT_USER = "{call insertUser(?, ?,?,?,?, ?,?)}";

	private static final String CALL_TRANSLATE_USER = "{call translateUser(?, ?,?,?,?, ?,?)}";

	private static final String CALL_UPDATE_USER = "{call updateUser(?, ?,?,?,?, ?,?)}";

	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	private final static String IS_USERNAME_AVAILABLE = "select u_user_name from user where u_user_name=?";
	private static final String SELECT_USER_SQL = "select * from user left join user_translator  on u_id = u_t_user where u_t_app_language=? and u_id=?";
	private static final String USER_ID = "u_id";
	private static final String FIRST_NAME = "u_t_first_name";
	private static final String DEFAULT_LANGUAGE = "en";
	private static final String LAST_NAME = "u_last_name";
	private static final String STREET_ADD = "u_add1";
	private static final String LOCALITY_ADD = "u_add2";
	private static final String EMAIL = "u_email";

	@Override
	public boolean saveUserData(RegisteredUser registeredUser) throws DAOException {
		Connection connection = null;

		boolean isInserted = false;
		boolean alreadyTaken = false;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			alreadyTaken = isUserNameAlreadyExists(connection, registeredUser);
			if (alreadyTaken == true) {
				throw new DAOException("UserName already Taken");
			}
			if (alreadyTaken == false) {
				isInserted = insertUser(connection, registeredUser);

			}

			connection.commit();
		} catch (DBManagerException | SQLException se) {

			try {
				connection.rollback();
				connection.setAutoCommit(true);
			} catch (SQLException ex) {

				throw new DAOException("Database Connectivity Exception ", ex);
			}
			throw new DAOException("Issue with DB parameters while " + "inserting user.", se);
		} finally {

			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}

			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DAOException("Issue with DB parameters while " + "setting auto commit.", e);
			}

		}

		return isInserted;
	}

	private boolean isUserNameAlreadyExists(Connection connection, RegisteredUser registeredUser) throws DAOException {
		PreparedStatement preparedStatement = null;
		int isTaken = 0;
		try {
			preparedStatement = connection.prepareStatement(IS_USERNAME_AVAILABLE);
			preparedStatement.setString(1, registeredUser.getUserName());
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				throw new DAOException("User Name already exists");
			}

		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
		} finally {
			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Statement Exception", e);
			}

		}
		return (isTaken != 0);
	}

	private boolean insertUser(Connection connection, RegisteredUser registeredUser) throws DAOException {

		int isInserted = 0;
		try {

			CallableStatement stmt = connection.prepareCall(CALL_INSERT_USER);
			stmt.setString(1, registeredUser.getUserName());
			stmt.setString(2, registeredUser.getPassword());
			stmt.setString(3, registeredUser.getFirstName());

			stmt.setString(4, registeredUser.getLastName());
			stmt.setString(5, registeredUser.getEmail());
			stmt.setString(6, registeredUser.getStreetAddress());
			stmt.setString(7, registeredUser.getLocalityAddress());
			isInserted = stmt.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);

		}
		return (isInserted != 0);
	}

	@Override
	public User getUserInfo(int id, String language) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User retrievedUser = null;
		try {
			
			connection = DBManager.getConnectionFromPool();
			retrievedUser = getData(connection, id, language);

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

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}
		}

		return retrievedUser;
	}

	private User getData(Connection connection, int id, String language) throws DAOException {
		PreparedStatement preparedStatement = null;
		User retrievedUser = null;
		ResultSet rs = null;

		try {
			preparedStatement = connection.prepareStatement(SELECT_USER_SQL);

			preparedStatement.setString(1, language);
			preparedStatement.setInt(2, id);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				retrievedUser = new User();
				retrievedUser.setUserId(rs.getInt(USER_ID));
				retrievedUser.setFirstName(rs.getString(FIRST_NAME));
				retrievedUser.setLastName(rs.getString(LAST_NAME));
				retrievedUser.setEmail(rs.getString(EMAIL));
				retrievedUser.setStreetAddress(rs.getString(STREET_ADD));
				retrievedUser.setLocalityAddress(rs.getString(LOCALITY_ADD));

			}
		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
		}

		if (retrievedUser == null) {
			language = DEFAULT_LANGUAGE;
			retrievedUser = getData(connection, id, language);
			return retrievedUser;
		}
		return retrievedUser;
	}

	@Override
	public boolean editUser(EditUserDTO user, String language) throws DAOException {
		Connection connection = null;
		int isInserted = 0;
		try {
			connection = DBManager.getConnectionFromPool();
			CallableStatement stmt = connection.prepareCall(CALL_UPDATE_USER);
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());

			stmt.setString(4, user.getStreetAddress());
			stmt.setString(5, user.getLocalityAddress());
			stmt.setInt(6, user.getUserId());
			stmt.setString(7, language);
			isInserted = stmt.executeUpdate();
		} catch (SQLException | DBManagerException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);

		} finally {

			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}
		}
		return (isInserted != 0);
	}

	@Override
	public boolean translateUser(EditUserDTO user, String language) throws DAOException {
		int isInserted = 0;
		
		Connection connection = null;
		try {
			connection = DBManager.getConnectionFromPool();
			CallableStatement stmt = connection.prepareCall(CALL_TRANSLATE_USER);

			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getEmail());

			stmt.setString(4, user.getStreetAddress());
			stmt.setString(5, user.getLocalityAddress());
			stmt.setInt(6, user.getUserId());
			stmt.setString(7, language);
			isInserted = stmt.executeUpdate();
		} catch (SQLException | DBManagerException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);

		} finally {

			try {
				DBManager.returnConnectionToPool(connection);

			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Connection Exception", e);
			}
		}
		return (isInserted != 0);
	}
}
