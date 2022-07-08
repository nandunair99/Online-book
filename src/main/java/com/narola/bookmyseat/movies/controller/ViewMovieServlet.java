package com.narola.bookmyseat.movies.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.GetMovieResult;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.ServiceFactory;

public class ViewMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			IMovieService iMovieService = ServiceFactory.getInstance().getmovieService();

			GetMovieResult getMovieResult = iMovieService.getMovie(movieId);
			request.setAttribute("movieDataById", getMovieResult.getMovieData());
			request.setAttribute("imgPosterById", getMovieResult.getImgPoster());
			request.setAttribute("imgBannerById", getMovieResult.getImgBanner());
			request.setAttribute("castCrewDataById", getMovieResult.getCastCrewData());
			request.setAttribute("imgCastCrewById", getMovieResult.getImgCastCrew());
			request.setAttribute("genreDataById", getMovieResult.getGenreData());
			request.setAttribute("languageDataById", getMovieResult.getLanguageData());

			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_VIEW_MOVIE)) {
				RequestDispatcher rd = request.getRequestDispatcher("AdminPage/ViewMovie.jsp");
				rd.forward(request, response);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_MOVIE_UPDATEDATA)) {
				RequestDispatcher rd = request.getRequestDispatcher("Movies");
				rd.forward(request, response);
			}
		} catch (ApplicationException e) {
			RequestDispatcher rd = request.getRequestDispatcher("Error");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
