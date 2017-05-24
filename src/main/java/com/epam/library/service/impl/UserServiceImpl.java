package com.epam.library.service.impl;

import com.epam.library.dao.UserDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.RegisteredUser;
import com.epam.library.service.UserService;
import com.epam.library.service.encryption.PasswordEncryptionAlgo;
import com.epam.library.service.exception.ServiceException;

public class UserServiceImpl implements UserService{

	
	@Override
	public boolean saveUser(RegisteredUser registeredUser) throws ServiceException {
	boolean flagInserted=false;
	
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO dao = daoFactory.getUserDao();
		byte[] plainText = ((String) registeredUser.getPassword()).getBytes();
		byte[] encryptedPassword = null;

		encryptedPassword = PasswordEncryptionAlgo.encryptedPassword(plainText);

		StringBuilder sb = PasswordEncryptionAlgo.createSb(encryptedPassword);
		registeredUser.setPassword(sb.toString());

		try {
			flagInserted=dao.saveUserData(registeredUser);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}

		return (flagInserted) ? true : false;
	}

}
