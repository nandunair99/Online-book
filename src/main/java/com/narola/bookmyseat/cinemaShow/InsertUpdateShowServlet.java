package com.narola.bookmyseat.cinemaShow;

import java.io.IOException;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.DTO.ShowDTO;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.entity.Show;
import com.narola.bookmyseat.entity.ShowPrice;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;
import com.narola.bookmyseat.utility.validationFunction;

public class InsertUpdateShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		IMovieDAO movieDAO = DAOFactory.getInstance().getmovieDAO();
		String showID = request.getParameter("txtShowId");
		String movieID = request.getParameter("txtMovieID");
		String showDate = request.getParameter("txtShowDate");
		String showTime = request.getParameter("txtShowTime");
		String endTime = "";
		String screenID = request.getParameter("txtScreenId");
		String[] SeatTypeID = request.getParameterValues("txtSeatTypeID");
		String[] SeatTypePrice = request.getParameterValues("txtSeatTypePrice");
		// String[] ShowPriceID = request.getParameterValues("txtShowPriceID");
		try {
			if (validationFunction.isNullOrEmpty(movieID)) {
				throw new ValidationException("validErrorMovieID", "Please select movie!");
			}
			if (validationFunction.isNullOrEmpty(showTime)) {
				throw new ValidationException("validErrorShowTime", "Please select show time!");
			}
			Movie movie = movieDAO.fetchMovieById(Integer.parseInt(movieID));
			LocalTime value = LocalTime.parse(showTime);
			value = value.plus(Duration.ofMinutes(
					ChronoUnit.MINUTES.between(LocalTime.MIDNIGHT, LocalTime.parse(movie.getDuration())) + 20));
			endTime = value.toString();
		} catch (ValidationException | DBException e) {
			request.setAttribute(((ValidationException) e).getField(), e.getMessage());
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_INSERT)) {
				request.setAttribute("validErrorShowInsert", Constant.ERROR_OCCURS_YES);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_UPDATE)) {
				request.setAttribute("validErrorShowUpdate", Constant.ERROR_OCCURS_YES);
			}
			RequestDispatcher rd = request.getRequestDispatcher("ManageShow");
			rd.forward(request, response);
			return;
		}

		ShowDTO showDTO = new ShowDTO();
		showDTO.setShowID(showID);
		showDTO.setMovieID(movieID);
		showDTO.setShowDate(showDate);
		showDTO.setShowTime(showTime);
		showDTO.setEndTime(endTime);
		showDTO.setScreenID(screenID);
		showDTO.setSeatTypeID(SeatTypeID);
		showDTO.setSeatTypePrice(SeatTypePrice);

		try {
			ShowValidator.validate(request, showDTO);
		} catch (ValidationException e) {
			e.printStackTrace();
			request.setAttribute("afterShowData", showDTO);
			request.setAttribute(e.getField(), e.getMessage());
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_INSERT)) {
				request.setAttribute("validErrorShowInsert", Constant.ERROR_OCCURS_YES);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_UPDATE)) {
				request.setAttribute("validErrorShowUpdate", Constant.ERROR_OCCURS_YES);
			}
			RequestDispatcher rd = request.getRequestDispatcher("ManageShow");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = request.getRequestDispatcher("ManageShow");
			rd.forward(request, response);
			return;
		}
		Show show = new Show();
		show.setMovieID(Integer.parseInt(movieID));
		show.setShowDate(Date.valueOf(showDate));
		show.setShowTime(showTime);
		show.setEndTime(endTime);
		show.setScreenID(Integer.parseInt(screenID));

		List<ShowPrice> showprice = new ArrayList<ShowPrice>();
		for (int i = 0; i < SeatTypeID.length; i++) {
			ShowPrice sp = new ShowPrice();
			sp.setSeatTypeID(Integer.parseInt(SeatTypeID[i]));
			sp.setPrice(Double.parseDouble(SeatTypePrice[i]));
			showprice.add(sp);
		}
		show.setShowPrice(showprice);
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_INSERT)) {
			try {
				ShowDAO.insertShow(show);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Show " + Constant.SUCCESS_TEXT_INSERTMSG);
			} catch (DBException e) {
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Show " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_UPDATE)) {
			show.setShowID(Integer.parseInt(showID));
			try {
				ShowDAO.updateShow(show);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Show " + Constant.SUCCESS_TEXT_UPDATEMSG);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Show " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("ManageShow");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
