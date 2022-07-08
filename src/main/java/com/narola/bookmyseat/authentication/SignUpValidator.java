package com.narola.bookmyseat.authentication;

import com.narola.bookmyseat.DTO.AddressDTO;
import com.narola.bookmyseat.DTO.UserDTO;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.validationFunction;

public class SignUpValidator {
	public static void validate(UserDTO userDTO,AddressDTO addressDTO)
	{
		if (validationFunction.isNullOrEmpty(userDTO.getFirstName())) {
			throw new ValidationException("validErrorFirstName", "Please enter first name!");
		}
		if (validationFunction.isNullOrEmpty(userDTO.getLastName())) {
			throw new ValidationException("validErrorLastName", "Please enter last name!");
		}
		if (validationFunction.isNullOrEmpty(userDTO.getEmail())) {
			throw new ValidationException("validErrorEmail", "Please enter email!");
		}
		if (validationFunction.isNullOrEmpty(userDTO.getPhone())) {
			throw new ValidationException("validErrorPhoneNo", "Please enter phone no!");
		}
		if (validationFunction.isNullOrEmpty(addressDTO.getAddressLine1())) {
			throw new ValidationException("validErrorAddressLine1", "Please enter addressline1!");
		}
		if (validationFunction.isNullOrEmpty(addressDTO.getAddressLine2())) {
			throw new ValidationException("validErrorAddressLine2", "Please enter addressline2!");
		}
		if (validationFunction.isNullOrEmpty(addressDTO.getLandmark())) {
			throw new ValidationException("validErrorLandmark", "Please enter landmark!");
		}
		if (validationFunction.isNullOrEmpty(addressDTO.getPincode())) {
			throw new ValidationException("validErrorPincode", "Please enter pincode!");
		}else {
			if (!validationFunction.isNumeric(addressDTO.getPincode())) {
				throw new ValidationException("validErrorPincode", "Please enter valid pincode!");
			}
		}
		if (validationFunction.isNullOrEmpty(addressDTO.getCity())) {
			throw new ValidationException("validErrorCity", "Please select city!");
		}
	}
}
