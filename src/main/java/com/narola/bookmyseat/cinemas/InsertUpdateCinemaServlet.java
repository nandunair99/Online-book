package com.narola.bookmyseat.cinemas;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.DTO.CinemaDTO;
import com.narola.bookmyseat.entity.Address;
import com.narola.bookmyseat.entity.Cinemas;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.DTO.AddressDTO;

public class InsertUpdateCinemaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession(false).getAttribute("LoginUser")!= null) {
			User user = (User) request.getSession(false).getAttribute("LoginUser");
			if(user.getUserTyp() != Constant.USER_TYPE_SUPERADMIN && user.getUserTyp() != Constant.USER_TYPE_ADMIN)
			{
				RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
			}
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
		}
		String cinemaId = request.getParameter("txtCinemaId");
		String cinemaName = request.getParameter("txtCinemaName");
		String cinematAvailableFacilities = request.getParameter("txtAvailableFacilities");
		String cinemaStatus = request.getParameter("txtStatus");
		
		String addressId = request.getParameter("txtAddressId");
		String addressLine1 = request.getParameter("txtCinemaAddLine1");
		String addressLine2 = request.getParameter("txtCinemaAddLine2");
		String landmark = request.getParameter("txtLandmark");
		String city = request.getParameter("txtCityId");
		String pincode = request.getParameter("txtPincode");
		
		CinemaDTO cinemaDTO = new CinemaDTO();
		cinemaDTO.setCinemaId(cinemaId);
		cinemaDTO.setCinemaName(cinemaName);
		cinemaDTO.setCinemaFacilities(cinematAvailableFacilities);
		cinemaDTO.setCinemaStatus(cinemaStatus);
		cinemaDTO.setAddressId(addressId);
		
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressId(addressId);
		addressDTO.setAddressLine1(addressLine1);
		addressDTO.setAddressLine2(addressLine2);
		addressDTO.setLandmark(landmark);
		addressDTO.setCity(city);
		addressDTO.setPincode(pincode);
		
		try {
			CinemaValidator.validate(request, cinemaDTO, addressDTO);
		}catch (ValidationException e) {
			request.setAttribute("afterCinemaData",cinemaDTO);
			request.setAttribute("afterAddressData",addressDTO);
			request.setAttribute(e.getField(), e.getMessage());
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_INSERT)) {
				request.setAttribute("validErrorCinemaInsert",Constant.ERROR_OCCURS_YES);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_UPDATE)) {
				request.setAttribute("validErrorCinemaUpdate",Constant.ERROR_OCCURS_YES);
			}
			RequestDispatcher rd = request.getRequestDispatcher("Cinemas");
			rd.forward(request, response);
			return;
		}catch (Exception e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = request.getRequestDispatcher("Cinemas");
			rd.forward(request, response);
			return;
		}
		
		//insert-update code
		Address address = new Address();
		address.setAddressline1(addressLine1);
		address.setAddressline2(addressLine2);
		address.setLandmark(landmark);
		address.setCity_ID(Integer.parseInt(city));
		address.setPincode(Integer.parseInt(pincode));
		
		Cinemas cinemas = new Cinemas();
		cinemas.setName(cinemaName);
		cinemas.setFacilities(cinematAvailableFacilities);
		cinemas.setStatus(Integer.parseInt(cinemaStatus));
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_INSERT)) {
			try {
				CinemasDAO.insertCinema(cinemas, address);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cinema " + Constant.SUCCESS_TEXT_INSERTMSG);
			}catch (DBException e) {
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cinema " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CINEMA_UPDATE)) {
			cinemas.setCinemasID(Integer.parseInt(cinemaId));
			cinemas.setAddressID(Integer.parseInt(addressId));
			address.setAddressID(Integer.parseInt(addressId));
			try {
				CinemasDAO.updateCinema(cinemas, address);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cinema " + Constant.SUCCESS_TEXT_UPDATEMSG);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cinema " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("Cinemas");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
