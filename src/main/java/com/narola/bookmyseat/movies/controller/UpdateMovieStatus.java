package com.narola.bookmyseat.movies.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.ServiceFactory;

public class UpdateMovieStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int movieId = Integer.parseInt(request.getParameter("movieId"));
			int movieStatus = Integer.parseInt(request.getParameter("status"));
			
			IMovieService iMovieService = ServiceFactory.getInstance().getmovieService();
			iMovieService.updateMovieStatus(movieId, movieStatus);
			
		} catch (ApplicationException e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Movie status: " + Constant.ERROR_TEXT_OPRATIONFAIL);
			RequestDispatcher rd = request.getRequestDispatcher("Movies");
			rd.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
