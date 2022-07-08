package com.narola.bookmyseat.cinemaScreen;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.narola.bookmyseat.entity.Screen;
import com.narola.bookmyseat.entity.ScreenSeatType;
import com.narola.bookmyseat.entity.SeatLayout;


public class ViewScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int screenID = Integer.parseInt(request.getParameter("screenId"));
		
		Screen ScreenData = ScreenDAO.fetchScreenById(screenID);
		request.setAttribute("ScreenData", ScreenData);
		
		List<ScreenSeatType> ScreenSeatTypeData = ScreenDAO.fetchAllScreenSeatType(screenID);
		request.setAttribute("ScreenSeatTypeData", ScreenSeatTypeData);
		
		List<SeatLayout> SeatLayoutData = ScreenDAO.fetchScreenSeatLayout(screenID);
		request.setAttribute("SeatLayoutData", SeatLayoutData);
		
		RequestDispatcher rd = request.getRequestDispatcher("CinemaAdminPage/ViewScreen.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
