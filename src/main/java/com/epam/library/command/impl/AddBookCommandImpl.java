package com.epam.library.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.library.command.Command;
import com.epam.library.domain.AddBookDTO;
import com.epam.library.service.BookService;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.factory.ServiceFactory;

public class AddBookCommandImpl implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
	AddBookDTO addBookDTO=new AddBookDTO();
	HttpSession session=request.getSession();
	boolean electronicBookList=false;
	addBookDTO.setTitle(request.getParameter("title"));
	addBookDTO.setDescription(request.getParameter("description"));
	addBookDTO.setAuthor(request.getParameter("author"));
	addBookDTO.setPrice(Float.parseFloat(request.getParameter("price")));
	addBookDTO.setBookProperty(request.getParameter("item"));
	addBookDTO.setTypeOfBook((String) session.getAttribute("bookTypeToBeAdded"));
	System.out.println(request.getParameter("item"));
	ServiceFactory serviceFactory = ServiceFactory.getInstance();
	BookService service = serviceFactory.getBookService();
	electronicBookList = service.addBook(addBookDTO);
	System.out.println(electronicBookList);

	return TargetPage.ADMIN_PAGE.getParam();
	}

}
