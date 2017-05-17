package com.epam.library.service;

import com.epam.library.domain.Request;
import com.epam.library.domain.User;
import com.epam.library.service.exception.ServiceException;

public interface UserService {

	

	User authenticateUser(Request userRequest,String actionName) throws ServiceException;



	

}
