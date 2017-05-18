package com.epam.library.servlet;

import java.io.IOException;
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
import com.epam.library.command.impl.RedirectServlet;
import com.epam.library.command.impl.UserBookByCategoryCommandImpl;
import com.epam.library.command.impl.UserLoginCommandImpl;
import com.epam.library.command.impl.UserLogoutCommandImpl;
import com.epam.library.command.impl.UserSelectedBookCommandImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Command> actionMap = new HashMap<String, Command>();
	private static final String LOGIN = "Login";
	private static final String LOGOUT = "Logout";
	private static final String ACTION = "action";
	private static final String REQUESTED_METHOD_TO_CALL = "requestedMethod";
	private static final String SEND_REDIRECT = "sendRedirect";
	private static final String FORWARD = "forward";
	private static final String REDIRECT_NEW_URL = "Welcome";
	private static final String BOOK_TO_FIND = "gettingBook";
	private static final String SELECTED_BOOK_TO_FIND = "gettingSelectedBook";

	/**
	 * Default constructor.
	 */
	public HomeServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		actionMap.put(LOGIN, new UserLoginCommandImpl());
		actionMap.put(LOGOUT, new UserLogoutCommandImpl());
		actionMap.put(REDIRECT_NEW_URL, new RedirectServlet());
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
		validatingRequestType(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		validatingRequestType(request, response);
	}

	protected void validatingRequestType(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String actionPage = null;
		String actionKey = request.getParameter(ACTION);
		actionPage = executingCommand(actionKey, request, response);
		if (request.getAttribute(REQUESTED_METHOD_TO_CALL) != null) {
			if (request.getAttribute(REQUESTED_METHOD_TO_CALL).equals(SEND_REDIRECT)) {
				response.sendRedirect(actionPage);
			} else if (request.getAttribute(REQUESTED_METHOD_TO_CALL).equals(FORWARD)) {
				RequestDispatcher rd = request.getRequestDispatcher(actionPage);
				rd.forward(request, response);
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
