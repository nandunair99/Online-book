package com.narola.bookmyseat.movies.dao;

import java.util.List;

import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.entity.Genre;
import com.narola.bookmyseat.entity.Language;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.MovieCastCrew;
import com.narola.bookmyseat.entity.MovieGenre;
import com.narola.bookmyseat.entity.MovieLanguage;
import com.narola.bookmyseat.exception.DBException;

public interface IMovieDAO {
	public List<Genre> fetchAllGenre() throws DBException;
	public List<Language> fetchAllLanguage() throws DBException;
	public int insertMovie(Movie movie,MovieCastCrew[] moviecastcrew,MovieGenre[] moviegenre,MovieLanguage[] movielanguage) throws DBException;
	public int updateMovie(Movie movie,MovieCastCrew[] moviecastcrew,MovieGenre[] moviegenre,MovieLanguage[] movielanguage) throws DBException;
	public List<Movie> fetchAllMovie() throws DBException;
	public Movie fetchMovieById(int movieId) throws DBException;
	public List<CastCrew> fetchMovieCastCrewByMovieId(int movieId) throws DBException;
	public List<Genre> fetchGenreByMovieId(int movieId) throws DBException;
	public List<Language> fetchLanguageByMovieId(int movieId) throws DBException;
	public int updateMovieStatus(int movieId,int Status) throws DBException;
	public int deleteMovie(int movieId) throws DBException;
	
}