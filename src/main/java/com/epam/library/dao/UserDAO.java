package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.exception.DBManagerException;
import com.epam.library.domain.RegisteredUser;

public interface UserDAO {

	boolean saveUserData(RegisteredUser registeredUser) throws DAOException;

}
