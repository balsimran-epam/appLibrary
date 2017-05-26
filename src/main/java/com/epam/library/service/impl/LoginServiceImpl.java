package com.epam.library.service.impl;

import com.epam.library.dao.LoginDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.DisplayBookDTO;
import com.epam.library.domain.User;
import com.epam.library.service.LoginService;
import com.epam.library.service.encryption.PasswordEncryptionAlgo;
import com.epam.library.service.exception.ServiceException;

public class LoginServiceImpl implements LoginService {

	@Override
	public User authenticateUser(DisplayBookDTO user, String actionName) throws ServiceException {

		User userDetails = null;
		DAOFactory daoFactory = DAOFactory.getInstance();
		LoginDAO dao = daoFactory.getLoginDao();
		byte[] plainText = ((String) user.getPassword()).getBytes();
		byte[] encryptedPassword = null;

		encryptedPassword = PasswordEncryptionAlgo.encryptedPassword(plainText);

		StringBuilder sb = PasswordEncryptionAlgo.createSb(encryptedPassword);
		user.setPassword(sb.toString());

		try {
			userDetails = dao.getUserData(user);
		} catch (DAOException se) {
			throw new ServiceException(se);
		}

	
		return userDetails;
	}

}
