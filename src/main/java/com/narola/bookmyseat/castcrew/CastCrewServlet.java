package com.narola.bookmyseat.castcrew;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;

public class CastCrewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ICastCrewDAO castCrewDAO = DAOFactory.getInstance().getcastcrewDAO();
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
		try {
			List<CastCrew> castCrewData = castCrewDAO.fetchAllCastCrew();
			
			request.setAttribute("castCrewdata", castCrewData);
			
			//for fill exist text into textBox after error
			request.setAttribute("txtCastCrewId",request.getAttribute("txtCastCrewId"));
			request.setAttribute("txtName",request.getAttribute("txtName"));
			request.setAttribute("txtDisImg",request.getAttribute("txtDisImg"));
			//errror msg
			request.setAttribute("validErrorCCName", request.getAttribute("validErrorCCName"));
			request.setAttribute("validErrorCCProfilePic", request.getAttribute("validErrorCCProfilePic"));
			//for check request it's Update or Insert (accordingly form will open)
			if(request.getAttribute("validErrorCCInsert")!=null)
			{
				request.setAttribute("validErrorCCInsert", request.getAttribute("validErrorCCInsert"));
			}
			if(request.getAttribute("validErrorCCUpdate")!=null)
			{
				request.setAttribute("validErrorCCUpdate", request.getAttribute("validErrorCCUpdate"));
			}
			RequestDispatcher rd = request.getRequestDispatcher("AdminPage/CastCrew.jsp");
			rd.forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("Error");
			rd.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
