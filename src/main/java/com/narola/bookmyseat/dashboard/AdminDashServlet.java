package com.narola.bookmyseat.dashboard;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.utility.Constant;

public class AdminDashServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		if(request.getSession(false).getAttribute("LoginUser")!= null) {
			User user = (User) request.getSession(false).getAttribute("LoginUser");
			if(user.getUserTyp() == Constant.USER_TYPE_SUPERADMIN || user.getUserTyp() == Constant.USER_TYPE_ADMIN)
			{
				request.setAttribute("LoginSuccess", Constant.ERROR_OCCURS_YES);
				RequestDispatcher rd = request.getRequestDispatcher("AdminPage/AdminDash.jsp");
				rd.forward(request, response);
			}else{
				response.sendRedirect("LoginServlet");
			}
		}else {
			response.sendRedirect("LoginServlet");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
