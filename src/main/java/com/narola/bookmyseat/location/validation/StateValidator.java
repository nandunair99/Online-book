package com.narola.bookmyseat.location.validation;

import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.utility.DAOFactory;
import com.narola.bookmyseat.utility.validationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StateValidator implements Validator {
    @Autowired
    ILocationDAO locationDAO;
    @Override
    public boolean supports(Class<?> clazz) {
        return State.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "stateName", "state.empty");
        State state = (State) target;
        if (!validationFunction.isAlphaSpace(state.getStateName())) {
            errors.rejectValue("stateName", "state.invalid");
        }
        if (locationDAO.fetchStateByName(state.getStateName()) != null) {
            errors.rejectValue("stateName", "state.exist", new Object[]{state.getStateName()}, "Already exist");
        }
    }
}
