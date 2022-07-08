package com.narola.bookmyseat.cinemaScreen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.Screen;
import com.narola.bookmyseat.utility.Constant;

public class UpdateScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int screenId = Integer.parseInt(request.getParameter("txtScreenId"));
		String screenName = request.getParameter("txtScreenName");

		Screen screen = new Screen();
		screen.setScreenID(screenId);
		screen.setScreenName(screenName);

		try {
			ScreenDAO.updateScreen(screen);
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.SUCCESS_TEXT_UPDATEMSG);
		} catch (Exception e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.ERROR_TEXT_OPRATIONFAIL);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("ViewScreen?screenId=" + screenId);
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
