package com.narola.bookmyseat.cinemaScreen;
import com.narola.bookmyseat.DTO.ScreenDTO;
import com.narola.bookmyseat.DTO.ScreenSeatTypeDTO;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.validationFunction;

public class ScreenValidator {
	public static void validate(ScreenDTO screenDTO, ScreenSeatTypeDTO screenseattypeDTO) throws ValidationException, DBException {
		if (validationFunction.isNullOrEmpty(screenDTO.getScreenName())) {
			throw new ValidationException("validErrorScreenName", "Please enter screen name!");
		}
		if (screenseattypeDTO.getSeatType() == null) {
			throw new ValidationException("validErrorScreenType", "Please add screen Type!");
		}
	}
}
