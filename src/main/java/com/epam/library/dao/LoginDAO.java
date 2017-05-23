package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;

public interface LoginDAO {

	User getUserData(Request user) throws DAOException;

	

}
