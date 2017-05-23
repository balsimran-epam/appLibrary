package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.command.requestMapping.ParameterSetter;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class UpdateBookCommandImpl implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
		AddBookDTO addBookDTO=new AddBookDTO();
	
		HttpSession session=request.getSession();
		ParameterSetter.setLanguage(request, session);	ParameterSetter.setLanguage(request, session);
		ParameterSetter.storingTypeOFBookToBeEdited(request, session);
		ParameterSetter.setIdOfSelectedBook(request, session);
		boolean electronicBookList=false;
		addBookDTO.setTitle(request.getParameter("title"));
		addBookDTO.setDescription(request.getParameter("description"));
		addBookDTO.setAuthor(request.getParameter("author"));
		addBookDTO.setPrice(Float.parseFloat(request.getParameter("price")));
		addBookDTO.setBookProperty(request.getParameter("item"));
		addBookDTO.setTypeOfBook((String) session.getAttribute("bookTypeToBeEdited"));
		addBookDTO.setBookId( (String) session.getAttribute("bookId"));
		System.out.println(request.getParameter("item"));
		addBookDTO.setLanguage((String) session.getAttribute(FormParamEnum.LANGUAGE.getParam()));
		System.out.println(addBookDTO.getLanguage());
		System.out.println(addBookDTO.getBookId());
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookService service = serviceFactory.getBookService();
		electronicBookList = service.editBook(addBookDTO);
		System.out.println("UPDATED"+electronicBookList);
		session.setAttribute("isUpdatedBook", electronicBookList);
		return TargetPage.ADMIN_PAGE.getParam();
	}

}
