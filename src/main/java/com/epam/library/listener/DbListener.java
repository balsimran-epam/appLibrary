package com.epam.library.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.epam.library.dao.DBManager;
import com.epam.library.dao.exception.DBManagerException;

/**
 * Application Lifecycle Listener implementation class DbListener
 *
 */
public class DbListener implements ServletContextListener {
	private static Logger logger = Logger.getLogger(DbListener.class);
	private static final String LOG_4J_PATH="log4j-config";
	

	/**
	 * Default constructor.
	 */
	public DbListener() {
		try {
			DBManager.closeConnections();
		} catch (DBManagerException e) {
			logger.log(Level.ERROR, "Exception occured",e);
		}
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
		
		DBManager.getInstance();
		
	}

}
