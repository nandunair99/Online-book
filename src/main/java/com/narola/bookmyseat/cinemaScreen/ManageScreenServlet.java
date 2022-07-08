package com.narola.bookmyseat.cinemaScreen;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.narola.bookmyseat.entity.Screen;

public class ManageScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Screen> ScreenData = ScreenDAO.fetchAllScreenByCinemaID(1);
		request.setAttribute("ScreenData", ScreenData);
		
		int chkLayout[] = new int[ScreenData.size()];
		for(int i=0;i<ScreenData.size();i++) {
			chkLayout[i] = ScreenDAO.checkLayoutCreate(ScreenData.get(i).getScreenID());
		}
		request.setAttribute("chkLayout", chkLayout);
		
		RequestDispatcher rd = request.getRequestDispatcher("CinemaAdminPage/ManageScreen.jsp");
		rd.forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
