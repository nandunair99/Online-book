package com.narola.bookmyseat.userhome;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.entity.Genre;
import com.narola.bookmyseat.entity.Language;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.utility.DAOFactory;

public class ExploreMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			IMovieDAO movieDAO = DAOFactory.getInstance().getmovieDAO();
			int movieId = Integer.parseInt(request.getParameter("MovieId"));
			Movie movieData = movieDAO.fetchMovieById(movieId);
			request.setAttribute("movieDataById", movieData);
			String imgPoster;
			String imgBanner;

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			final byte[] bytes = new byte[1024];
			int read = 0;
			while ((read = movieData.getMoviePoster().read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			imgPoster = Base64.getEncoder().encodeToString(os.toByteArray());
			request.setAttribute("imgPosterById", imgPoster);

			ByteArrayOutputStream os2 = new ByteArrayOutputStream();
			final byte[] bytes2 = new byte[1024];
			int read2 = 0;
			while ((read2 = movieData.getMovieBanner().read(bytes2)) != -1) {
				os2.write(bytes2, 0, read2);
			}
			imgBanner = Base64.getEncoder().encodeToString(os2.toByteArray());
			request.setAttribute("imgBannerById", imgBanner);

			List<CastCrew> castCrewData = movieDAO.fetchMovieCastCrewByMovieId(movieId);
			request.setAttribute("castCrewDataById", castCrewData);

			List<String> imgCastCrew = new ArrayList<>();
			for (CastCrew castcrew : castCrewData) {
				imgCastCrew.add(Base64.getEncoder().encodeToString(castcrew.getProfileImg()));
			}
			request.setAttribute("imgCastCrewById", imgCastCrew);

			List<Genre> genreData = movieDAO.fetchGenreByMovieId(movieId);
			request.setAttribute("genreDataById", genreData);

			List<Language> languageData = movieDAO.fetchLanguageByMovieId(movieId);
			request.setAttribute("languageDataById", languageData);

			RequestDispatcher rd = request.getRequestDispatcher("UserPage/ExploreMovie.jsp");
			rd.forward(request, response);
		} catch (DBException | IOException e) {
			// to jsp
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
