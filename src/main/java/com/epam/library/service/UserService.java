package com.epam.library.service;

import com.epam.library.domain.RegisteredUser;
import com.epam.library.service.exception.ServiceException;

public interface UserService {

	boolean saveUser(RegisteredUser registeredUser) throws ServiceException;

}
