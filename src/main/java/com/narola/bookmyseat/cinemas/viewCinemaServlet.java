package com.narola.bookmyseat.cinemas;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.Cinemas;
import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;

public class viewCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ILocationDAO locationDAO = DAOFactory.getInstance().getlocationDAO();
		if (request.getSession(false).getAttribute("LoginUser") != null) {
			User user = (User) request.getSession(false).getAttribute("LoginUser");
			if (user.getUserTyp() != Constant.USER_TYPE_SUPERADMIN && user.getUserTyp() != Constant.USER_TYPE_ADMIN) {
				RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
		}
		int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));

		List<State> StateData = locationDAO.fetchAllState();
		request.setAttribute("Statedata", StateData);

		List<City> CityData = locationDAO.fetchAllCity();
		request.setAttribute("Citydata", CityData);

		Cinemas cinemaData = CinemasDAO.fetchAllCinemasByID(cinemaId);
		request.setAttribute("cinemaDataById", cinemaData);
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_VIEW_CINEMA)) {
			RequestDispatcher rd = request.getRequestDispatcher("AdminPage/ViewCinema.jsp");
			rd.forward(request, response);
		} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_UPDATEDATA)) {
			RequestDispatcher rd = request.getRequestDispatcher("Cinemas");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
