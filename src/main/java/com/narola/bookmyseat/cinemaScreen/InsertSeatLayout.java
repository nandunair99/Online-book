package com.narola.bookmyseat.cinemaScreen;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.SeatLayout;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.Constant;

public class InsertSeatLayout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int screenid = Integer.parseInt(request.getParameter("screenId"));
		String[] selectedSeat = request.getParameterValues("seat");
		String[] allHiddenSeat = request.getParameterValues("hiddenseat");
		if (selectedSeat.length > 0) {
			for (int i = 0; i < allHiddenSeat.length; i++) {
				if (Arrays.stream(selectedSeat).anyMatch(allHiddenSeat[i]::equals)) {
					allHiddenSeat[i] = allHiddenSeat[i] + ",1";
				} else {
					allHiddenSeat[i] = allHiddenSeat[i] + ",0";
				}
			}
			for (int i = 0; i < allHiddenSeat.length; i++) {
				System.out.println("Record:" + allHiddenSeat[i]);
			}
			SeatLayout seatlayout[] = new SeatLayout[allHiddenSeat.length];
			for (int i = 0; i < allHiddenSeat.length; i++) {
				seatlayout[i] = new SeatLayout();
				String[] res = allHiddenSeat[i].split("[,]", 0);

				seatlayout[i].setSeatNo(res[0]);
				seatlayout[i].setSeatTypeID(Integer.parseInt(res[1]));
				seatlayout[i].setStatus(Integer.parseInt(res[2]));
			}
			try {
				ScreenDAO.insertSeatLayout(seatlayout);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.SUCCESS_TEXT_INSERTMSG);
			} catch (DBException e) {
				e.printStackTrace();
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
			RequestDispatcher rd = request.getRequestDispatcher("ViewScreen?screenId=" + screenid);
			rd.forward(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("ViewScreen?screenId=" + screenid);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
