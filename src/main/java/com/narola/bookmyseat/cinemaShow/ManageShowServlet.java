package com.narola.bookmyseat.cinemaShow;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.cinemaScreen.ScreenDAO;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.Screen;
import com.narola.bookmyseat.entity.ScreenSeatType;
import com.narola.bookmyseat.entity.Show;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.utility.DAOFactory;

public class ManageShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			IMovieDAO movieDAO = DAOFactory.getInstance().getmovieDAO();
			
			List<Show> showData = ShowDAO.fetchShowDataByCinemaID(1);
			request.setAttribute("ShowData", showData);
			
			List<Movie> movieData = movieDAO.fetchAllMovie();
			request.setAttribute("MovieData", movieData);
			
			List<Screen> screenData = ScreenDAO.fetchAllScreenByCinemaID(1);
			request.setAttribute("ScreenData", screenData);
			
			List<ScreenSeatType> seatTypeData = ScreenDAO.fetchAllScreenSeatType();
			request.setAttribute("SeatTypeData", seatTypeData);
			
			RequestDispatcher rd = request.getRequestDispatcher("CinemaAdminPage/ManageShow.jsp");
			rd.forward(request, response);
		} catch (DBException | IOException e) {
			// to jsp
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
