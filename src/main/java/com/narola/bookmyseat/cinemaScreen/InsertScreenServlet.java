package com.narola.bookmyseat.cinemaScreen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.DTO.ScreenDTO;
import com.narola.bookmyseat.DTO.ScreenSeatTypeDTO;
import com.narola.bookmyseat.entity.Screen;
import com.narola.bookmyseat.entity.ScreenSeatType;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;

public class InsertScreenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String txtScreenName = request.getParameter("txtScreenName");
		String[] txtSeatType = request.getParameterValues("txtSeatType");
		String[] txtnoOfRow = request.getParameterValues("txtnoOfRow");

		ScreenDTO screenDTO = new ScreenDTO();
		screenDTO.setScreenName(txtScreenName);
		screenDTO.setCinemaID("1");

		ScreenSeatTypeDTO screenseattypeDTO = new ScreenSeatTypeDTO();
		screenseattypeDTO.setSeatType(txtSeatType);
		screenseattypeDTO.setNoOfRow(txtnoOfRow);

		try {
			ScreenValidator.validate(screenDTO, screenseattypeDTO);
		} catch (ValidationException e) {
			request.setAttribute("afterScreenData", screenDTO);
			request.setAttribute("afterSeatTypeData", screenseattypeDTO);
			request.setAttribute(e.getField(), e.getMessage());
			request.setAttribute("validErrorScreenInsert", Constant.ERROR_OCCURS_YES);
			RequestDispatcher rd = request.getRequestDispatcher("ManageScreen");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = request.getRequestDispatcher("ManageScreen");
			rd.forward(request, response);
			return;
		}

		Screen screen = new Screen();
		screen.setScreenName(txtScreenName);
		screen.setCinemaID(1);

		String strSeatType[] = txtSeatType;
		String strNoOfRow[] = txtnoOfRow;
		ScreenSeatType seatType[] = new ScreenSeatType[strSeatType.length];
		System.out.println("Insert Screen:" + strSeatType.length);
		for (int i = 0; i < strSeatType.length; i++) {
			seatType[i] = new ScreenSeatType();
			seatType[i].setSeatType(strSeatType[i]);
			seatType[i].setNoOfRow(Integer.parseInt(strNoOfRow[i]));
		}
		try {
			ScreenDAO.insertScreen(screen, seatType);
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.SUCCESS_TEXT_INSERTMSG);
		} catch (DBException e) {
			e.printStackTrace();
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Screen " + Constant.ERROR_TEXT_OPRATIONFAIL);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("ManageScreen");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
