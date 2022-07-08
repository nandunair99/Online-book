package com.narola.bookmyseat.movies.service;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.entity.GetMovieResult;
import com.narola.bookmyseat.entity.GetMoviesResult;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.MovieCastCrew;
import com.narola.bookmyseat.entity.MovieGenre;
import com.narola.bookmyseat.entity.MovieLanguage;
import com.narola.bookmyseat.exception.ApplicationException;
import org.springframework.stereotype.Service;

@Service
public interface IMovieService {
	void insertUpdateMovie(Movie movie,MovieCastCrew[] moviecastcrew,MovieGenre[] moviegenre,MovieLanguage[] movielanguage, HttpServletRequest request) throws ApplicationException;

	GetMoviesResult getAllMovie() throws ApplicationException;

	void deleteMovie(int movieId) throws ApplicationException;

	void updateMovieStatus(int movieId, int movieStatus) throws ApplicationException;

	GetMovieResult getMovie(int movieId) throws ApplicationException;
}
