package com.narola.bookmyseat.authentication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.narola.bookmyseat.DTO.AddressDTO;
import com.narola.bookmyseat.DTO.UserDTO;
import com.narola.bookmyseat.entity.Address;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;

public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("txtFirstName");
		String lastName = request.getParameter("txtLastName");
		String email = request.getParameter("txtEmail");
		String phone = request.getParameter("txtPhone");
		String type = String.valueOf(Constant.USER_TYPE_ENDUSER);
		Part profile = request.getPart("txtProfile");

		String addressLine1 = request.getParameter("txtAddressLine1");
		String addressLine2 = request.getParameter("txtAddressLine2");
		String landmark = request.getParameter("txtLandmark");
		String cityID = request.getParameter("txtCityId");
		String pincode = request.getParameter("txtPincode");

		UserDTO userDTO = new UserDTO();
		userDTO.setFirstName(firstName);
		userDTO.setLastName(lastName);
		userDTO.setEmail(email);
		userDTO.setPhone(phone);
		userDTO.setType(type);
		userDTO.setProfile(profile);

		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1(addressLine1);
		addressDTO.setAddressLine2(addressLine2);
		addressDTO.setLandmark(landmark);
		addressDTO.setCity(cityID);
		addressDTO.setPincode(pincode);

		try {
			SignUpValidator.validate(userDTO, addressDTO);
		} catch (ValidationException e) {
			request.setAttribute("afterUserData", userDTO);
			request.setAttribute("afterAddressData", addressDTO);
			request.setAttribute(e.getField(), e.getMessage());
			request.setAttribute("validErrorUser", Constant.ERROR_OCCURS_YES);

			RequestDispatcher rd = request.getRequestDispatcher("SignUp");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = request.getRequestDispatcher("SignUp");
			rd.forward(request, response);
			return;
		}

		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmailID(email);
		user.setPhoneno(phone);
		user.setUserTyp(Integer.parseInt(type));
		InputStream profileIS = null;

		if (profile.getSize() != 0) {
			profileIS = profile.getInputStream();
		} else {
			File defaultProfile = new File(
					request.getServletContext().getRealPath("/") + "resources/img/avatars/user.png");
			profileIS = (InputStream) new FileInputStream(defaultProfile);
		}
		user.setProfileImg(profileIS);

		Address address = new Address();
		address.setAddressline1(addressLine1);
		address.setAddressline2(addressLine2);
		address.setLandmark(landmark);
		address.setCity_ID(Integer.parseInt(cityID));
		address.setPincode(Integer.parseInt(pincode));

		try {
			AuthenticationDAO.insertUser(user, address);
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
			request.setAttribute(Constant.ERROR_TEXTMSG, "User " + Constant.SUCCESS_TEXT_INSERTMSG);
		} catch (Exception e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, "User " + Constant.ERROR_TEXT_OPRATIONFAIL);
		}
		RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
