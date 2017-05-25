package com.epam.library.service;

import com.epam.library.domain.EditUserDTO;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.domain.User;
import com.epam.library.service.exception.ServiceException;

public interface UserService {

	boolean saveUser(RegisteredUser registeredUser) throws ServiceException;

	User getUserData(int id, String language) throws ServiceException;

	boolean updateUser(EditUserDTO user, String language) throws ServiceException;

}
