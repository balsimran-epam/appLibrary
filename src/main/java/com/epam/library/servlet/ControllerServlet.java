package com.epam.library.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
import com.epam.library.command.impl.AddBookCommandImpl;
import com.epam.library.command.impl.BookTypeCommand;
import com.epam.library.command.impl.EditBookCommandImpl;
import com.epam.library.command.impl.LibraryLanguageCommand;
import com.epam.library.command.impl.SearchBookCommandImpl;
import com.epam.library.command.impl.TranslateUserCommandImpl;
import com.epam.library.command.impl.UpdateBookCommandImpl;
import com.epam.library.command.impl.UpdateUserCommandImpl;
import com.epam.library.command.impl.UserBookByCategoryCommandImpl;
import com.epam.library.command.impl.UserLoginCommandImpl;
import com.epam.library.command.impl.UserLogoutCommandImpl;
import com.epam.library.command.impl.UserSelectedBookCommandImpl;
import com.epam.library.command.impl.UserSignUpCommandImpl;
import com.epam.library.command.impl.WelcomeToAddFormCommand;
import com.epam.library.command.impl.WelcomeToAdminPageCommand;
import com.epam.library.command.impl.WelcomeToEditCommad;
import com.epam.library.command.impl.WelcomeToEditProfileCommand;
import com.epam.library.command.impl.WelcomeToUserCommand;

/**
 * Servlet implementation class LoginServlet
 */
public class ControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Command> actionMap = new HashMap<String, Command>();
	private static final String CHARACTER_ENCODING = "UTF-8";
	private static final String LOGIN = "Login";
	private static final String LOGOUT = "Logout";
	private static final String SIGN_UP = "SignUp";
	private static final String ACTION = "action";
	private static final String REDIRECT_NEW_URL = "Welcome";
	private static final String BOOK_TO_FIND = "gettingBook";
	private static final String SELECTED_BOOK_TO_FIND = "gettingSelectedBook";
	private static final String ERROR_FLAG = "flag";
	private static final String ADD_BOOK_FORM = "bookType";
	private static final String SHOW_ADD_FORM = "gettingPage";
	private static final String ADD_BOOK = "addBook";
	private static final String SHOW_EDIT_FORM = "editType";
	private static final String EDIT_BOOK = "editBook";
	private static final String UPDATE_BOOK = "updateBook";
	private static final String ADMIN_ADDED_BOOK = "WelcomeToAdd";
	private static final String COMMAND_LANGUAGE_STORE = "languageChanged";
	private static final String SEARCH_BOOK = "search";
	private static final String GET_EDIT_PROFILE_VIEW = "editUserForm";
	private static final String UPDATE_USER = "updateUserInfo";
	private static final String TRANSLATE_USER = "translateUser";

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
		actionMap.put(SIGN_UP, new UserSignUpCommandImpl());
		actionMap.put(SHOW_ADD_FORM, new BookTypeCommand());
		actionMap.put(ADD_BOOK_FORM, new WelcomeToAddFormCommand());
		actionMap.put(ADD_BOOK, new AddBookCommandImpl());
		actionMap.put(EDIT_BOOK, new EditBookCommandImpl());
		actionMap.put(SHOW_EDIT_FORM, new WelcomeToEditCommad());
		actionMap.put(UPDATE_BOOK, new UpdateBookCommandImpl());
		actionMap.put(ADMIN_ADDED_BOOK, new WelcomeToAdminPageCommand());
		actionMap.put(COMMAND_LANGUAGE_STORE, new LibraryLanguageCommand());
		actionMap.put(SEARCH_BOOK, new SearchBookCommandImpl());
		actionMap.put(GET_EDIT_PROFILE_VIEW, new WelcomeToEditProfileCommand());
		actionMap.put(UPDATE_USER, new UpdateUserCommandImpl());
		actionMap.put(TRANSLATE_USER, new TranslateUserCommandImpl());
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
		request.setCharacterEncoding(CHARACTER_ENCODING);
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
		request.setCharacterEncoding(CHARACTER_ENCODING);
		String dispatcher = null;
		String actionKey = request.getParameter(ACTION);

		dispatcher = executingCommand(actionKey, request, response);
		if (dispatcher != null) {
			String error = (String) request.getAttribute(ERROR_FLAG);
			if (error != null && !error.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher(dispatcher);
				rd.forward(request, response);
			} else {
				response.sendRedirect(dispatcher);
			}

		}
	}

	protected String executingCommand(String urlToExecute, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {

		String actionPage = null;
		Command command;
		command = actionMap.get(urlToExecute);
		actionPage = command.execute(request, response);
		return actionPage;

	}

}
