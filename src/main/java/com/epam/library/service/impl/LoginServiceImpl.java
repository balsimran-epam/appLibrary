package com.epam.library.service.impl;

import com.epam.library.dao.UserDAO;
import com.epam.library.dao.exception.DAOException;
import com.epam.library.dao.factory.DAOFactory;
import com.epam.library.domain.Request;
import com.epam.library.domain.User;
import com.epam.library.service.UserService;
import com.epam.library.service.encryption.PasswordEncryptionAlgo;
import com.epam.library.service.exception.ServiceException;
import com.epam.library.service.exception.ValidatorException;
import com.epam.library.service.util.Validator;

public class LoginServiceImpl implements UserService {

	@Override
	public User authenticateUser(Request user, String actionName) throws ServiceException {
		try {
			Validator.userIdValidation(user, actionName);
		} catch (ValidatorException ve) {
			throw new ServiceException(ve);
		}
		User userDetails = new User();
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO dao = daoFactory.getUserDao();
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
