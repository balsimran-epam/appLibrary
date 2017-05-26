package com.epam.library.dao;

import com.epam.library.dao.exception.DAOException;
import com.epam.library.domain.EditUserDTO;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.domain.User;

public interface UserDAO {

	boolean saveUserData(RegisteredUser registeredUser) throws DAOException;

	User getUserInfo(int id, String language) throws DAOException;

	boolean editUser(EditUserDTO user, String language) throws DAOException;

	boolean translateUser(EditUserDTO user, String language)throws DAOException;
}
