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
import javax.servlet.http.HttpSession;

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
	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String ACTION = "action";
	private static final String REQUESTED_METHOD_TO_CALL = "requestedMethod";
	private static final String SEND_REDIRECT = "sendRedirect";
	private static final String FORWARD = "forward";
	private static final String REDIRECT_NEW_URL = "redirect";
	private static final String NEW_URL = "toForwarded";
	private static final String BOOK_TO_FIND = "gettingBook";
	private static final String SELECTED_BOOK_TO_FIND = "gettingSelectedBook";
	private static final String TYPE_OF_BOOK = "bookSelectedType";

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
		
		HttpSession session = request.getSession();
		String actionPage = null;
		if (request.getParameter("typeOfBook") != null) {
			session.setAttribute(TYPE_OF_BOOK, request.getParameter("typeOfBook"));
			request.setAttribute(TYPE_OF_BOOK, request.getParameter("typeOfBook"));
		} else
			session.setAttribute(TYPE_OF_BOOK, session.getAttribute(TYPE_OF_BOOK));
		
		if (request.getParameter("language") != null) {
			session.setAttribute("language", request.getParameter("language"));
			request.setAttribute("language", request.getParameter("language"));
		} else
			session.setAttribute(TYPE_OF_BOOK, session.getAttribute(TYPE_OF_BOOK));
		String actionKey = request.getParameter("getSelectedBook");
		actionKey = checkForNullId(actionKey, request);
		session.setAttribute("action",actionKey);
		request.setAttribute("bookId", request.getParameter("bookId"));
		if (actionKey == null) {
			accessingprivateFiles(actionKey, request, response);

		} else
			actionPage = executingCommand(actionKey, request, response);
		validatingRequestType(request, response, actionPage);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
HttpSession session=request.getSession();
		String actionPage = null;
		request.setAttribute(USER_NAME, request.getParameter(USER_NAME));
		request.setAttribute(PASSWORD, request.getParameter(PASSWORD));
		String actionKey = request.getParameter(ACTION);
		session.setAttribute("action",request.getParameter(ACTION));

		if (actionKey == null) {

			accessingprivateFiles(actionKey, request, response);

		} else
			actionPage = executingCommand(actionKey, request, response);
		validatingRequestType(request, response, actionPage);

	}

	protected void accessingprivateFiles(String actionKey, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String actionPage = null;
		if (actionKey == null) {
			String forwardedURL = (String) session.getAttribute(NEW_URL);
			actionPage = executingCommand(forwardedURL, request, response);
			RequestDispatcher rd = request.getRequestDispatcher(actionPage);
			rd.forward(request, response);
		}

	}

	protected String executingCommand(String urlToExecute, HttpServletRequest request, HttpServletResponse response) {
		String actionPage = null;
		Command command;
		command = actionMap.get(urlToExecute);
		actionPage = command.execute(request, response);
		return actionPage;

	}

	protected void validatingRequestType(HttpServletRequest request, HttpServletResponse response, String actionPage)
			throws IOException, ServletException {
		if (request.getAttribute(REQUESTED_METHOD_TO_CALL) != null) {
			if (request.getAttribute(REQUESTED_METHOD_TO_CALL).equals(SEND_REDIRECT)) {
				response.sendRedirect(actionPage);
			}

			else if (request.getAttribute(REQUESTED_METHOD_TO_CALL).equals(FORWARD)) {
				RequestDispatcher rd = request.getRequestDispatcher(actionPage);
				rd.forward(request, response);
			}
		}
	}

	protected String checkForNullId(String actionKey, HttpServletRequest request) {
		if (actionKey == null) {
			actionKey = request.getParameter("getBook");
		}

		return actionKey;
	}
}
