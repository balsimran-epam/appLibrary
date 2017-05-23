package com.epam.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.library.service.exception.ServiceException;

public interface Command {
	String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException ;
}
