package com.epam.library.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.library.dao.DBManager;

/**
 * Application Lifecycle Listener implementation class DbListener
 *
 */
public class DbListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(DbListener.class);
	private static final String LOG_4J_PATH="log4j-config";
	private static final String URL="url";
	private static final String USER_NAME="user_name";
	private static final String PASSWORD="password";
	private static final String DRIVER="driver";

	/**
	 * Default constructor.
	 */
	public DbListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String log4jConfigFile = context.getInitParameter(LOG_4J_PATH);
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
		PropertyConfigurator.configure(fullPath);
		ServletContext sc = event.getServletContext();
		String url = sc.getInitParameter(URL);
		String user_name = sc.getInitParameter(USER_NAME);
		String password = sc.getInitParameter(PASSWORD);
		String driver = sc.getInitParameter(DRIVER);
		DBManager db = null;

		try {
			db = new DBManager(url, user_name, password, driver);
		} catch (Exception e) {
			logger.log(Level.ERROR, "Exception was thrown", e);
		}
		
	}

}
