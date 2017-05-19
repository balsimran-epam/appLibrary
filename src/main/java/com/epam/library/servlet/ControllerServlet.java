package com.epam.library.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.library.command.Command;
import com.epam.library.command.impl.WelcomeToUserCommand;
import com.epam.library.dao.builder.exception.BuilderException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.command.impl.UserBookByCategoryCommandImpl;
import com.epam.library.command.impl.UserLoginCommandImpl;
import com.epam.library.command.impl.UserLogoutCommandImpl;
import com.epam.library.command.impl.UserSelectedBookCommandImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> actionMap = new HashMap<String, Command>();
	private static final String LOGIN = "Login";
	private static final String LOGOUT = "Logout";
	private static final String ACTION = "action";
	private static final String REDIRECT_NEW_URL = "Welcome";
	private static final String BOOK_TO_FIND = "gettingBook";
	private static final String SELECTED_BOOK_TO_FIND = "gettingSelectedBook";
	private static final String ERROR_FLAG = "flag";

	/**
	 * Default constructor.
	 */
	public ControllerServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		actionMap.put(LOGIN, new UserLoginCommandImpl());
		actionMap.put(LOGOUT, new UserLogoutCommandImpl());
		actionMap.put(REDIRECT_NEW_URL, new WelcomeToUserCommand());
		actionMap.put(BOOK_TO_FIND, new UserBookByCategoryCommandImpl());
		actionMap.put(SELECTED_BOOK_TO_FIND, new UserSelectedBookCommandImpl());
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String dispatcher = null;
		String actionKey = request.getParameter(ACTION);

		dispatcher = executingCommand(actionKey, request, response);

		if (dispatcher != null) {
			RequestDispatcher rd = request.getRequestDispatcher(dispatcher);
			rd.forward(request, response);
		}

	}

	/**
	 * @throws ServletException
	 * @throws IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String dispatcher = null;
		String actionKey = request.getParameter(ACTION);

		dispatcher = executingCommand(actionKey, request, response);

		if (dispatcher != null) {
			if (request.getAttribute(ERROR_FLAG) == "true") {
				RequestDispatcher rd = request.getRequestDispatcher(dispatcher);
				rd.forward(request, response);
			} else {
				response.sendRedirect(dispatcher);
			}

		}
	}

	protected String executingCommand(String urlToExecute, HttpServletRequest request, HttpServletResponse response) {
		String actionPage = null;
		Command command;
		command = actionMap.get(urlToExecute);
		actionPage = command.execute(request, response);
		return actionPage;

	}

}
