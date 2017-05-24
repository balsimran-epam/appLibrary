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
import com.epam.library.dao.builder.exception.BuilderException;
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

		boolean isInserted = false;
		boolean alreadyTaken = false;
		try {
			connection = DBManager.getConnectionFromPool();

			connection.setAutoCommit(false);
			alreadyTaken = isUserNameAlreadyExists(connection, registeredUser);
			if (alreadyTaken == true) {
				throw new BuilderException("UserName already Taken");
			}
			if (alreadyTaken == false) {
				isInserted = insertUser(connection, registeredUser);
				System.out.println(isInserted);
			}

			connection.commit();
		} catch (DBManagerException | BuilderException | SQLException se) {

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
		System.out.println("in fn");
		PreparedStatement preparedStatement = null;
		int isTaken = 0;
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
		return (isTaken != 0);
	}

	private boolean insertUser(Connection connection, RegisteredUser registeredUser) throws DAOException {

		int isInserted = 0;
		try {

			CallableStatement stmt = connection.prepareCall("{call insertUser(?, ?,?,?,?, ?,?)}");
			System.out.println("in insertion" + registeredUser.getEmail());
			stmt.setString(1, registeredUser.getUserName());
			stmt.setString(2, registeredUser.getPassword());
			stmt.setString(3, registeredUser.getFirstName());

			stmt.setString(4, registeredUser.getLastName());
			stmt.setString(5, registeredUser.getEmail());
			stmt.setString(6, registeredUser.getStreetAddress());
			stmt.setString(7, registeredUser.getLocalityAddress());
			isInserted = stmt.executeUpdate();
			System.out.println("is inserted" + isInserted);
		} catch (SQLException ex) {
			throw new DAOException("Database Connectivity Exception ", ex);

		}
		return (isInserted != 0);
	}
}
