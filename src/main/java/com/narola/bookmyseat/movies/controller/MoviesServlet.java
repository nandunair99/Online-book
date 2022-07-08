package com.narola.bookmyseat.movies.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.GetMoviesResult;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.ServiceFactory;

public class MoviesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			/*IMovieService iMovieService = ServiceFactory.getInstance().getmovieService();
			GetMoviesResult getMoviesResult = iMovieService.getAllMovie();

			request.setAttribute("GenreData", getMoviesResult.getGenreData());
			request.setAttribute("LanguageData", getMoviesResult.getLanguageData());
			request.setAttribute("CastCrewData", getMoviesResult.getCastCrewData());
			request.setAttribute("MovieData", getMoviesResult.getMovieData());
			request.setAttribute("imgPoster", getMoviesResult.getImgPoster());*/
			
			//for Update
			request.setAttribute("movieDataById", request.getAttribute("movieDataById"));
			request.setAttribute("imgPosterById", request.getAttribute("imgPosterById"));
			request.setAttribute("imgBannerById", request.getAttribute("imgBannerById"));
			request.setAttribute("castCrewDataById", request.getAttribute("castCrewDataById"));
			request.setAttribute("genreDataById", request.getAttribute("genreDataById"));
			request.setAttribute("languageDataById", request.getAttribute("languageDataById"));
			
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.ERROR_TEXT_OPRATIONFAIL);
		}
		RequestDispatcher rd = request.getRequestDispatcher("AdminPage/Movies.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
