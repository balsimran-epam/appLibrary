package com.epam.library.service;

import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.User;
import com.epam.library.service.exception.ServiceException;

public interface LoginService {

	

	User authenticateUser(DisplayBookDTO userRequest,String actionName) throws ServiceException;



	

}
