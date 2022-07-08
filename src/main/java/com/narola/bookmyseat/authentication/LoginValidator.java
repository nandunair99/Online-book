package com.narola.bookmyseat.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.DTO.LoginDTO;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.validationFunction;

public class LoginValidator {
	public static void validate(HttpServletRequest request,LoginDTO loginDTO)throws ValidationException, IOException {
		if (validationFunction.isNullOrEmpty(loginDTO.getEmailId())) {
			throw new ValidationException("validErrorEmail", "Please enter email!");
		}else {
			if(!validationFunction.isValidEmail(loginDTO.getEmailId())) {
				throw new ValidationException("validErrorEmail", "Invalid email!");
			}
		}
		if (validationFunction.isNullOrEmpty(loginDTO.getPassword())) {
			throw new ValidationException("validErrorPassword", "Please enter password!");
		}else {
			if(loginDTO.getPassword().length() < 4)
			{
				throw new ValidationException("validErrorPassword", "Password length must be of 4-12!");
			}
		}
		if (validationFunction.isNullOrEmpty(loginDTO.getType())) {
			throw new ValidationException("validErrorType", "Please select type!");
		}
	
	}
}
