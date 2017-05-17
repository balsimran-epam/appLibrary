package com.epam.library.service.util;

import com.epam.library.domain.Request;
import com.epam.library.service.exception.ValidatorException;

public class Validator {
	public static void userIdValidation(Request user, String action) throws ValidatorException {

		if (!user.getUserName().matches("[A-Z][a-zA-Z]*")) {

			throw new ValidatorException("Your UserName doesn't matches with pattern");
		}

	}

	public static void validatingSelectedBookType(Request request) throws ValidatorException {
		if (request.getTypeOfBook()==null) {

			throw new ValidatorException("Please Select Type of book");
		}
		
	}
}
