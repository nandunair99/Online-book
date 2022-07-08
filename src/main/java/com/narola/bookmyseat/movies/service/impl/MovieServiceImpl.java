package com.narola.bookmyseat.movies.service.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.entity.Genre;
import com.narola.bookmyseat.entity.GetMovieResult;
import com.narola.bookmyseat.entity.GetMoviesResult;
import com.narola.bookmyseat.entity.Language;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.MovieCastCrew;
import com.narola.bookmyseat.entity.MovieGenre;
import com.narola.bookmyseat.entity.MovieLanguage;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;
import com.narola.bookmyseat.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements IMovieService {

	@Autowired
	private IMovieDAO movieDAO;
	@Autowired
	private ICastCrewDAO castCrewDAO ;

	/*public MovieServiceImpl(MovieServiceBuilder movieServiceBuilder) {
		this.movieDAO = movieServiceBuilder.getMovieDAO();
		this.castCrewDAO = movieServiceBuilder.getCastCrewDAO();
	}*/

	public void insertUpdateMovie(Movie movie, MovieCastCrew[] moviecastcrew, MovieGenre[] moviegenre,
			MovieLanguage[] movielanguage, HttpServletRequest request) throws ApplicationException {
		try {
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_INSERT)) {

				movieDAO.insertMovie(movie, moviecastcrew, moviegenre, movielanguage);

			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_UPDATE)) {

				movieDAO.updateMovie(movie, moviecastcrew, moviegenre, movielanguage);
			}
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public GetMoviesResult getAllMovie() throws ApplicationException {
		try {
			List<Genre> genreData = movieDAO.fetchAllGenre();
			List<Language> languageData = movieDAO.fetchAllLanguage();
			List<CastCrew> castCrewData = castCrewDAO.fetchAllCastCrew();
			List<Movie> movieData = movieDAO.fetchAllMovie();

			GetMoviesResult getMoviesResult = new GetMoviesResult();
			getMoviesResult.setGenreData(genreData);
			getMoviesResult.setLanguageData(languageData);
			getMoviesResult.setCastCrewData(castCrewData);
			getMoviesResult.setMovieData(movieData);

			List<String> imgPoster = new ArrayList<>();
			for (Movie movie : movieData) {
				imgPoster.add(ImageUtility.inputstreamToImage(movie.getMoviePoster()));
			}
			getMoviesResult.setImgPoster(imgPoster);
			return getMoviesResult;
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public void deleteMovie(int movieId) throws ApplicationException {
		try {
			movieDAO.deleteMovie(movieId);

		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public void updateMovieStatus(int movieId, int movieStatus) throws ApplicationException {
		try {
			movieDAO.updateMovieStatus(movieId, movieStatus);
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);

		}
	}

	@Override
	public GetMovieResult getMovie(int movieId) throws ApplicationException {

		try {
			Movie movieData = movieDAO.fetchMovieById(movieId);
			String imgPoster;
			String imgBanner;

			imgPoster = ImageUtility.inputstreamToImage(movieData.getMoviePoster());
			imgBanner = ImageUtility.inputstreamToImage(movieData.getMovieBanner());

			List<CastCrew> castCrewData = movieDAO.fetchMovieCastCrewByMovieId(movieId);

			List<String> imgCastCrew = new ArrayList<>();
			for (CastCrew castcrew : castCrewData) {
				imgCastCrew.add(Base64.getEncoder().encodeToString(castcrew.getProfileImg()));
			}
			List<Genre> genreData = movieDAO.fetchGenreByMovieId(movieId);
			List<Language> languageData = movieDAO.fetchLanguageByMovieId(movieId);

			GetMovieResult getMovieResult = new GetMovieResult();
			getMovieResult.setMovieData(movieData);
			getMovieResult.setImgPoster(imgPoster);
			getMovieResult.setImgBanner(imgBanner);
			getMovieResult.setCastCrewData(castCrewData);
			getMovieResult.setImgCastCrew(imgCastCrew);
			getMovieResult.setGenreData(genreData);
			getMovieResult.setLanguageData(languageData);

			return getMovieResult;
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}

	}
}
