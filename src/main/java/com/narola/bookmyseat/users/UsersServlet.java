package com.narola.bookmyseat.users;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.narola.bookmyseat.cinemas.CinemasDAO;
import com.narola.bookmyseat.entity.CinemaAdmin;
import com.narola.bookmyseat.entity.Cinemas;
import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;

public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ILocationDAO locationDAO = DAOFactory.getInstance().getlocationDAO();
		HttpSession session= request.getSession();
		try {
			if(session.getAttribute("LoginUser")!= null) {
				User user = (User) session.getAttribute("LoginUser");
				if(user.getUserTyp() == Constant.USER_TYPE_SUPERADMIN)
				{
					List<Cinemas> CinemaData = CinemasDAO.fetchAllCinemas();
					request.setAttribute("CinemaData", CinemaData);
					
					List<State> StateData = locationDAO.fetchAllState();
					request.setAttribute("Statedata", StateData);
					
					List<City> CityData = locationDAO.fetchAllCity();
					request.setAttribute("Citydata", CityData);
					
					List<User> UserData = UsersDAO.fetchAllUsers();
					request.setAttribute("UserData",UserData);
					
					List<CinemaAdmin> CinemaAdmin = UsersDAO.fetchCinemaAdmin();
					request.setAttribute("CinemaAdmin",CinemaAdmin);		
					
					List<String> profileImg = new ArrayList<String>();
					for (User user2 : UserData) {
						ByteArrayOutputStream os = new ByteArrayOutputStream(); 
						final byte[] bytes = new byte[1024];
						int read = 0;
						while ((read = user2.getProfileImg().read(bytes)) != -1) {
							os.write(bytes, 0, read);
						}
						profileImg.add(Base64.getEncoder().encodeToString(os.toByteArray()));
					}
					
					request.setAttribute("profileImg", profileImg);
					
					
					request.setAttribute("LoginSuccess", Constant.ERROR_OCCURS_YES);
					RequestDispatcher rd = request.getRequestDispatcher("AdminPage/Users.jsp");
					rd.forward(request, response);
				}else
				{
					response.sendRedirect("LoginServlet");
				}
			}else {
				response.sendRedirect("LoginServlet");
			}
		}catch (Exception e) {
			response.sendRedirect("LoginServlet");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
