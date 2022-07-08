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

import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.utility.DAOFactory;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			IMovieDAO movieDAO = DAOFactory.getInstance().getmovieDAO();
			int cityId = 0;

			if (request.getParameter("cityId") != null) {
				cityId = Integer.parseInt(request.getParameter("cityId"));
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("SelectCity");
				rd.forward(request, response);
			}

			List<Movie> movieData = HomeDAO.fetchRunningMovie(cityId);
			request.setAttribute("MovieData", movieData);

			List<String> imgPoster = new ArrayList<>();
			for (Movie movie : movieData) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				final byte[] bytes = new byte[1024];
				int read = 0;
				while ((read = movie.getMoviePoster().read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				imgPoster.add(Base64.getEncoder().encodeToString(os.toByteArray()));
			}
			request.setAttribute("imgPoster", imgPoster);

			List<Movie> allMovieData = movieDAO.fetchAllMovie();
			request.setAttribute("AllMovieData", allMovieData);

			List<String> upimgPoster = new ArrayList<>();
			for (Movie movie : allMovieData) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				final byte[] bytes = new byte[1024];
				int read = 0;
				while ((read = movie.getMoviePoster().read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				upimgPoster.add(Base64.getEncoder().encodeToString(os.toByteArray()));
			}
			request.setAttribute("upimgPoster", upimgPoster);
			RequestDispatcher rd = request.getRequestDispatcher("UserPage/Home.jsp");
			rd.forward(request, response);
		} catch (DBException e) {
			//to jsp
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
