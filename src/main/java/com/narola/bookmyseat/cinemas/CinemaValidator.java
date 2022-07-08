package com.narola.bookmyseat.cinemas;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.DTO.AddressDTO;
import com.narola.bookmyseat.DTO.CinemaDTO;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.validationFunction;

public class CinemaValidator {
	public static void validate(CinemaDTO cinemaDTO,AddressDTO addressDTO) throws ValidationException, DBException {
		if (validationFunction.isNullOrEmpty(cinemaDTO.getCinemaName())) {
			throw new ValidationException("validErrorCinemaName", "Please enter cinema name!");
		} else {
			if (!validationFunction.isAlphaSpaceNumeric(cinemaDTO.getCinemaName())) {
				throw new ValidationException("validErrorCinemaName", "Please enter valid cinema name!");
			}
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
	public static void validate(HttpServletRequest request, CinemaDTO cinemaDTO ,AddressDTO addressDTO) throws ValidationException, DBException {
		validate(cinemaDTO,addressDTO);
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_UPDATE)) {
			if (validationFunction.isNullOrEmpty(cinemaDTO.getCinemaId())) {
				throw new ValidationException("validErrorCinemaID", "Please provide cinema ID");
			}
		}
	}
}
