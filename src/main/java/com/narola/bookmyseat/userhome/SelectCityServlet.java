package com.narola.bookmyseat.userhome;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.utility.DAOFactory;

public class SelectCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILocationDAO locationDAO = DAOFactory.getInstance().getlocationDAO();
		
		List<State> StateData = locationDAO.fetchAllState();
		request.setAttribute("Statedata", StateData);
		
		List<City> CityData = HomeDAO.fetchCinemaCity();
		request.setAttribute("Citydata", CityData);
		
		RequestDispatcher rd = request.getRequestDispatcher("UserPage/SelectCity.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
