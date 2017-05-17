package com.epam.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.exception.DBManagerException;

public class DBManager {
	static List<Connection> availableConnections = new ArrayList<Connection>();
	static int DB_MAX_CONNECTIONS = 5;
	static String url;
	static String user_name;
	static String password;
	String driver;

	public DBManager(String url, String user_name, String password, String driver) throws DBManagerException {
		this.url = url;
		this.user_name = user_name;
		this.password = password;
		this.driver = driver;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

/*		initializeConnectionPool();
*/	}

	/*private void initializeConnectionPool() throws DBManagerException {
		while (!checkIfConnectionPoolIsFull()) {

			availableConnections.add(createNewConnectionForPool());

		}
	}*/

	/*private synchronized boolean checkIfConnectionPoolIsFull() {

		if (availableConnections.size() < DB_MAX_CONNECTIONS) {
			return false;
		}

		return true;
	}*/

	public static Connection createNewConnectionForPool() throws DBManagerException {

		try {

			Connection connection = (Connection) DriverManager.getConnection(url, user_name, password);
			return connection;
		} catch (SQLException e) {
			throw new DBManagerException("Database exception", e);
		}

	}

	public static synchronized void returnConnectionToPool(Connection connection) {
		availableConnections.add(connection);
	}

	public static synchronized void removeConnectionPool() {
		availableConnections.removeAll(availableConnections);
	}

	public static void closeStatement(Statement statement) throws DBManagerException {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException ex) {
			throw new DBManagerException("Closing Connection Exception", ex);
		}
	}

	public static void closeConnection(Connection connection) throws DBManagerException {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException ex) {
			throw new DBManagerException("Connection Exception", ex);
		}
	}

	public static void closeStatement(PreparedStatement preparedStatement) throws DBManagerException {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException ex) {
			throw new DBManagerException("Prepared Statement Exception", ex);
		}
	}

}
