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
import com.epam.library.domain.RegisteredUser;

public class UserDaoImpl implements UserDAO {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);

	private final static String insertTableSQL = " INSERT INTO user"
			+ "( u_id, u_user_name, u_password,u_role,u_user_role) VALUES" + "(?,?,?,?,?)";
	private final static String getLatestId = "select u_id from user";
	private final static String IS_USERNAME_AVAILABLE = "select u_user_name from user where u_user_name=?";
	private final static String inserDataSQL = "INSERT INTO user_translator(u_t_first_name,u_last_name,u_email,u_add1,u_add2,u_t_app_language,u_t_user)VALUES(?,?,?,?,?,?,?)";

	@Override
	public boolean saveUserData(RegisteredUser registeredUser) throws DAOException {

		Connection connection = null;
		boolean isDataSaved = false;
		boolean isUserNameAvailable = false;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			isUserNameAlreadyExists(connection, registeredUser);

			int id = getLastId(connection);

			int newId = id + 1;
			registeredUser.setUserId(newId);
			boolean isInserted = insertDataInUserTable(registeredUser, connection, id);

			if (isInserted != false)
				isDataSaved = saveDataToUser(connection, registeredUser, id);

			connection.commit();
		} catch (SQLException | DBManagerException se) {

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
		return isDataSaved;
	}

	private boolean isUserNameAlreadyExists(Connection connection, RegisteredUser registeredUser) throws DAOException {
		System.out.println("in fn");
		PreparedStatement preparedStatement = null;
		int isAvailable = 0;
		try {
			preparedStatement = connection.prepareStatement(IS_USERNAME_AVAILABLE);
			System.out.println(registeredUser.getUserName());
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
		return (isAvailable != 0);
	}

	private boolean insertDataInUserTable(RegisteredUser registeredUser, Connection connection, int id)
			throws DAOException {

		int isInserted = 0;
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(insertTableSQL);

			preparedStatement.setInt(1, (id + 1));
			preparedStatement.setString(2, registeredUser.getUserName());
			preparedStatement.setString(3, registeredUser.getPassword());
			preparedStatement.setInt(4, 2);

			preparedStatement.setInt(5, 2);

			isInserted = preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
		} finally {
			try {

				DBManager.closeStatement(preparedStatement);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Statement Exception", e);
			}

		}
		return (isInserted != 0);

	}

	private boolean saveDataToUser(Connection connection, RegisteredUser registeredUser, int id) throws DAOException {
		PreparedStatement ps = null;
		int isInserted = 0;

		System.out.println(id + 1);
		System.out.println(registeredUser.getUserId());
		System.out.println(registeredUser.getLanguage());
		try {
			ps = connection.prepareStatement(inserDataSQL);

			ps.setString(1, registeredUser.getFirstName());
			ps.setString(2, registeredUser.getLastName());
			ps.setString(3, registeredUser.getEmail());
			ps.setString(4, registeredUser.getStreetAddress());
			ps.setString(5, registeredUser.getLocalityAddress());
			ps.setString(6, registeredUser.getLanguage());
			ps.setInt(7, registeredUser.getUserId());

			isInserted = ps.executeUpdate();
			System.out.println(isInserted);
		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
		} finally {
			try {

				DBManager.closeStatement(ps);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Statement Exception", e);
			}
		}

		return (isInserted != 0);
	}

	private int getLastId(Connection connection) throws DAOException {
		PreparedStatement ps = null;
		int id = 0;

		try {
			ps = connection.prepareStatement(getLatestId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);
		} finally {
			try {

				DBManager.closeStatement(ps);
			} catch (DBManagerException e) {
				logger.log(Level.ERROR, "Closing Statement Exception", e);
			}
		}

		return id;

	}
}
