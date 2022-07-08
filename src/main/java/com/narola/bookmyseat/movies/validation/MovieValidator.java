package com.narola.bookmyseat.movies.validation;

import javax.servlet.http.HttpServletRequest;
import com.narola.bookmyseat.DTO.MovieDTO;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.validationFunction;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class MovieValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		return Movie.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "movieTitle", "movieTitle.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duration", "duration.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "censorRating", "censorRating.empty");

		Movie movie = (Movie) target;
		if (validationFunction.isNullOrEmpty((movie.getReleaseDate()).toString())) {
			errors.rejectValue("releaseDate", "releaseDate.empty");
		}
		if (!validationFunction.isAlphaSpaceBackslash(movie.getCensorRating())) {
			errors.rejectValue("censorRating", "censorRating.invalid");
		}
		if (movie.getGenre() == null) {
			errors.rejectValue("genre", "genre.empty");
		}
		if (movie.getLanguage() == null) {
			errors.rejectValue("language", "language.empty");
		}
		if (movie.getCastCrewId() == null) {
			errors.rejectValue("castCrewId", "castCrewId.empty");
		}
	}
}
