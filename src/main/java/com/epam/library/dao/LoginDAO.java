package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.User;

public interface LoginDAO {

	User getUserData(DisplayBookDTO user) throws DAOException;

	

}
