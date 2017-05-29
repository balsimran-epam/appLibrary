package com.epam.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import com.epam.library.dao.exception.DBMangaerRuntimeException;
import com.epam.library.dao.exception.DBManagerException;

public class DBManager {

	private static final int DB_MAX_CONNECTIONS = 2;

	private static BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<Connection>(
			DB_MAX_CONNECTIONS);
	private static BlockingQueue<Connection> usedConnection = new ArrayBlockingQueue<Connection>(DB_MAX_CONNECTIONS);
	private static final ResourceBundle rb = ResourceBundle.getBundle("bundle");

	private static final String JDBC_DRIVER = "jdbcDriver";
	private static final String URL_DATABASE = "url";
	private static final String USERNAME = "userName";
	private static final String PASSWORD = "password";
	private static final String USER = "user";

	private static Properties info;

	private DBManager() {
		try {
			loadDriver();
			for (int i = 0; i < DB_MAX_CONNECTIONS; i++) {
				Connection connection = openConnection();
				if (connection == null) {
					throw new DBMangaerRuntimeException("No Connection is provided");
				}
				availableConnections.put(connection);
			}
		} catch (InterruptedException e) {
			throw new DBMangaerRuntimeException("Problem while putting " + "connection in available connections.", e);
		}
	}

	static {

		info = new Properties();
		info.put(USER, rb.getString(USERNAME));
		info.put(PASSWORD, rb.getString(PASSWORD));

	}

	public static DBManager getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public static Connection getConnectionFromPool() throws DBManagerException {
		Connection connection = null;

		try {
			connection = availableConnections.take();
			usedConnection.put(connection);
		} catch (InterruptedException ie) {
			throw new DBManagerException("Exception occured while getting or returning connection" + ie);
		}
		return connection;
	}

	public static void returnConnectionToPool(Connection connection) throws DBManagerException {

		try {
			availableConnections.put(connection);
		} catch (InterruptedException e) {
			throw new DBManagerException("Problem during adding connection back to available connections", e);
		}
		usedConnection.remove(connection);
	}

	public static void closeConnections() throws DBManagerException {

		try {
			for (Connection connection : availableConnections) {
				connection.close();
			}
			for (Connection connection : usedConnection) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DBManagerException("Problem while closing all connections", e);
		}
	}

	private void loadDriver() {
		try {
			Class.forName(rb.getString(JDBC_DRIVER));
		} catch (ClassNotFoundException e) {
			throw new DBMangaerRuntimeException("Problem during loading MySQL Driver.", e);
		}
	}

	private Connection openConnection() {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(rb.getString(URL_DATABASE), info);
		} catch (SQLException e) {
			throw new DBMangaerRuntimeException("Problem while getting connection", e);
		}
		return connection;
	}

	private static class SingletonHelper {
		private static final DBManager INSTANCE = new DBManager();
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
