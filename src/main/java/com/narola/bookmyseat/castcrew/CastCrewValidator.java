package com.narola.bookmyseat.castcrew;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.DTO.CastCrewDTO;
import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;
import com.narola.bookmyseat.utility.validationFunction;

public class CastCrewValidator {
	
	public static void validate(CastCrewDTO castCrewDTO) throws ValidationException, DBException {
		if (validationFunction.isNullOrEmpty(castCrewDTO.getCastCrewName())) {
			throw new ValidationException("validErrorCCName", "Please enter cast-crew name!");
		} else {                                            
			if (!validationFunction.isAlphaSpace(castCrewDTO.getCastCrewName())) {
				throw new ValidationException("validErrorCCName", "Please enter valid cast-crew name!");
			}
		}
	}
	static ICastCrewDAO castCrewDAO = DAOFactory.getInstance().getcastcrewDAO();
	public static void validate(HttpServletRequest request, CastCrewDTO castCrewDTO)
			throws ValidationException, DBException {
		validate(castCrewDTO);
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CASTCREW_UPDATE)) {
			if (validationFunction.isNullOrEmpty(castCrewDTO.getCastCrewId())) {
				throw new ValidationException("validErrorCityID", "Please provide cast-crew ID!");
			}
		}
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CASTCREW_INSERT)) {
			if (validationFunction.isNullOrEmpty(castCrewDTO.getCastCrewProfile().getSubmittedFileName())) {
				throw new ValidationException("validErrorCCProfilePic", "Please select proflie photo!");
			}
			if (castCrewDAO.fetchCastCrewByName(castCrewDTO.getCastCrewName()) != null) {
				throw new ValidationException("validErrorCCName", castCrewDTO.getCastCrewName() + " already exist.");
			}
		}
	}
}
