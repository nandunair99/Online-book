package com.narola.bookmyseat.movies.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.DTO.MovieDTO;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.MovieCastCrew;
import com.narola.bookmyseat.entity.MovieGenre;
import com.narola.bookmyseat.entity.MovieLanguage;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DateUtility;
import com.narola.bookmyseat.utility.ServiceFactory;

public class InsertUpdateMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			MovieDTO movieDTO = (MovieDTO) request.getAttribute("movieDTO");

			Date releaseDate = null;
			Timestamp castCrewCreatedTime = null;
			Timestamp genreCreatedTime = null;
			Timestamp languageCreatedTime = null;
			java.sql.Date sqlReleaseDate = null;
			// movie
			Movie movie = new Movie();
			// movieCastCrew
			String[] castCrewID = movieDTO.getCastCrewId();
			String[] castCrewType = movieDTO.getType();
			MovieCastCrew[] moviecastcrew = new MovieCastCrew[castCrewID.length];

			// movieGenre
			String[] genreID = movieDTO.getGenre();
			MovieGenre[] moviegenre = new MovieGenre[genreID.length];

			// movieLanguage
			String[] languageID = movieDTO.getLanguage();
			MovieLanguage[] movielanguage = new MovieLanguage[languageID.length];

			releaseDate = DateUtility.parseDate(DateUtility.format_yyyy_MM_dd, movieDTO.getReleaseDate());
			sqlReleaseDate = new java.sql.Date(releaseDate.getTime());

			castCrewCreatedTime = new Timestamp(DateUtility
					.parseDate(DateUtility.format_yyyy_MM_dd_hh_mm_ss, movieDTO.getCastCrewCreatedTime()).getTime());

			genreCreatedTime = new Timestamp(DateUtility
					.parseDate(DateUtility.format_yyyy_MM_dd_hh_mm_ss, movieDTO.getGenreCreatedTime()).getTime());

			languageCreatedTime = new Timestamp(DateUtility
					.parseDate(DateUtility.format_yyyy_MM_dd_hh_mm_ss, movieDTO.getLanguageCreatedTime()).getTime());

			// movie
			movie.setMovieTitle(movieDTO.getMovieName());
			movie.setReleaseDate(sqlReleaseDate);
			movie.setDuration(movieDTO.getDuration());
			movie.setCensorRating(movieDTO.getCensorRating());
			movie.setDescription(movieDTO.getMovieDescription());
			movie.setStatus(Constant.STATUS_MOVIE_INACTIVE);
			movie.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			movie.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

			// movieCastCrew
			for (int i = 0; i < castCrewID.length; i++) {
				moviecastcrew[i] = new MovieCastCrew();
				moviecastcrew[i].setCastCrewID(Integer.parseInt(castCrewID[i]));
				moviecastcrew[i].setCastCrewType(Integer.parseInt(castCrewType[i]));
				moviecastcrew[i].setCreatedTime(new Timestamp(System.currentTimeMillis()));
				moviecastcrew[i].setUpdatedTime(new Timestamp(System.currentTimeMillis()));
			}
			// movieGenre
			for (int i = 0; i < genreID.length; i++) {
				moviegenre[i] = new MovieGenre();
				moviegenre[i].setGenreID(Integer.parseInt(genreID[i]));
				moviegenre[i].setCreatedTime(new Timestamp(System.currentTimeMillis()));
				moviegenre[i].setUpdatedTime(new Timestamp(System.currentTimeMillis()));
			}
			// movieLanguage
			for (int i = 0; i < languageID.length; i++) {
				movielanguage[i] = new MovieLanguage();
				movielanguage[i].setLanguageID(Integer.parseInt(languageID[i]));
				movielanguage[i].setCreatedTime(new Timestamp(System.currentTimeMillis()));
				movielanguage[i].setUpdatedTime(new Timestamp(System.currentTimeMillis()));
			}
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_INSERT)) {
				// for banner and poster (when empty set default else set selected)
				InputStream filecontentBanner = null;
				InputStream filecontentPoster = null;
				if (movieDTO.getMovieBanner().getSize() != 0) {
					filecontentBanner = movieDTO.getMovieBanner().getInputStream();
				} else {
					File defaultBanner = new File(request.getServletContext().getRealPath("/")
							+ "resources/img/defaultImg/defaultBanner.jpg");
					filecontentBanner = new FileInputStream(defaultBanner);
				}
				if (movieDTO.getMoviePoster().getSize() != 0) {
					filecontentPoster = movieDTO.getMoviePoster().getInputStream();
				} else {
					File defaultPoster = new File(request.getServletContext().getRealPath("/")
							+ "resources/img/defaultImg/defaultPoster.jpg");
					filecontentPoster = new FileInputStream(defaultPoster);
				}
				movie.setMoviePoster(filecontentPoster);
				movie.setMovieBanner(filecontentBanner);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_UPDATE)) {
				for (int i = 0; i < castCrewID.length; i++) {
					moviecastcrew[i].setCreatedTime(castCrewCreatedTime);
				}
				for (int i = 0; i < genreID.length; i++) {
					moviegenre[i].setCreatedTime(genreCreatedTime);
				}
				for (int i = 0; i < languageID.length; i++) {
					movielanguage[i].setCreatedTime(languageCreatedTime);
				}

				movie.setStatus(Integer.parseInt(movieDTO.getStatus()));

				InputStream filecontentBanner = null;
				InputStream filecontentPoster = null;
				if (movieDTO.getMovieBanner().getSize() != 0) {
					filecontentBanner = movieDTO.getMovieBanner().getInputStream();
				}
				if (movieDTO.getMoviePoster().getSize() != 0) {
					filecontentPoster = movieDTO.getMoviePoster().getInputStream();
				}
				movie.setMoviePoster(filecontentPoster);
				movie.setMovieBanner(filecontentBanner);
				movie.setMovieID(Integer.parseInt(movieDTO.getMovieId()));
			}

			IMovieService iMovieService = ServiceFactory.getInstance().getmovieService();
			iMovieService.insertUpdateMovie(movie, moviecastcrew, moviegenre, movielanguage, request);

			if (request.getRequestURI ().equals(request.getContextPath() + Constant.URL_MOVIE_INSERT)) {
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.SUCCESS_TEXT_INSERTMSG);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_UPDATE)) {
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.SUCCESS_TEXT_UPDATEMSG);
			}

		} catch (ApplicationException | ParseException e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.ERROR_TEXT_OPRATIONFAIL);
		}
		RequestDispatcher rd = request.getRequestDispatcher("Movies");
		rd.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
