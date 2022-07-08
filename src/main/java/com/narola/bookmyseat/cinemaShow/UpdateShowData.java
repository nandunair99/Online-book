package com.narola.bookmyseat.cinemaShow;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.Show;

public class UpdateShowData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int showID = Integer.parseInt(request.getParameter("showID"));
		Show ShowData = ShowDAO.fetchShowDataByID(showID);
		request.setAttribute("UpdateShowData", ShowData);
		RequestDispatcher rd = request.getRequestDispatcher("ManageShow");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
