package com.narola.bookmyseat.location.validation;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.utility.validationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CityValidator implements Validator {
    @Autowired
    ILocationDAO locationDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //ILocationDAO locationDAO = DAOFactory.getInstance().getlocationDAO();
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cityName", "city.empty");
        City city = (City) target;
        if (city.getStateId() <= 0) {
            errors.rejectValue("stateId", "stateId.empty");
        }
        if (!validationFunction.isAlphaSpace(city.getCityName())) {
            errors.rejectValue("cityName", "city.invalid");
        }
        if (locationDAO.fetchCityByNameWithState(city.getCityName(), city.getStateId()) != null) {
            errors.rejectValue("cityName", "city.exist", new Object[]{city.getCityName()}, "Already exist");
        }
    }
}
