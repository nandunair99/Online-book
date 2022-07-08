package com.narola.bookmyseat.authentication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.bookmyseat.DTO.LoginDTO;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;

public class LoginActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		String type = request.getParameter("txtType");

		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmailId(email);
		loginDTO.setPassword(password);
		loginDTO.setType(type);
		try {
			LoginValidator.validate(request, loginDTO);
			
			User user = AuthenticationDAO.checkAuth(loginDTO.getEmailId(), loginDTO.getPassword(), loginDTO.getType());
			if (user == null) {
				throw new ValidationException("validErrorLogin", "Invalid login details!");
			} else {
				HttpSession session = request.getSession();
				

				String profile = "";
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				final byte[] bytes = new byte[1024];
				int read = 0;
				while ((read = user.getProfileImg().read(bytes)) != -1) {
					os.write(bytes, 0, read);
				}
				profile = Base64.getEncoder().encodeToString(os.toByteArray());
				user.setProfileImgAsBase64(profile);
				session.setAttribute("LoginUser", user);
			}
		} catch (ValidationException e) {
			e.printStackTrace();
			request.setAttribute("afterLoginData", loginDTO);
			request.setAttribute(e.getField(), e.getMessage());
			request.setAttribute("validError", Constant.ERROR_OCCURS_YES);
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
			return;
		}

		if (Integer.parseInt(type) == Constant.USER_TYPE_ADMIN
				|| Integer.parseInt(type) == Constant.USER_TYPE_SUPERADMIN) {
			RequestDispatcher rd = request.getRequestDispatcher("AdminDash");
			rd.forward(request, response);
		}
		if (Integer.parseInt(type) == Constant.USER_TYPE_CINEMAADMIN
				|| Integer.parseInt(type) == Constant.USER_TYPE_SUPERCINEMAADMIN) {
			RequestDispatcher rd = request.getRequestDispatcher("CinemaAdminDashboard");
			rd.forward(request, response);
		}
		if (Integer.parseInt(type) == Constant.USER_TYPE_ENDUSER) {
			RequestDispatcher rd = request.getRequestDispatcher("SelectCity");
			rd.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
