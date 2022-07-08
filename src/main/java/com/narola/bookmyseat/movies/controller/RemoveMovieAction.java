package com.narola.bookmyseat.movies.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.ServiceFactory;
import com.narola.bookmyseat.utility.validationFunction;

public class RemoveMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int movieId = 0;
			if (validationFunction.isNullOrEmpty(request.getParameter("movieId"))) {
				throw new ValidationException("validErrorMovieID", "Please provide movie ID!");
			} else {
				movieId = Integer.parseInt(request.getParameter("movieId"));
			}

			IMovieService iMovieService = ServiceFactory.getInstance().getmovieService();
			iMovieService.deleteMovie(movieId);
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.SUCCESS_TEXT_DELETEMSG);

		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, Constant.ERROR_OCCURS_OPSMSG);
		} catch (ValidationException e) {
			request.setAttribute(e.getField(), e.getMessage());
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
