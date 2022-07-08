package com.narola.bookmyseat.castcrew;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;
import com.narola.bookmyseat.utility.validationFunction;

public class RemoveCCAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ICastCrewDAO castCrewDAO = DAOFactory.getInstance().getcastcrewDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession(false).getAttribute("LoginUser") != null) {
			User user = (User) request.getSession(false).getAttribute("LoginUser");
			if (user.getUserTyp() != Constant.USER_TYPE_SUPERADMIN && user.getUserTyp() != Constant.USER_TYPE_ADMIN) {
				RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("LoginServlet");
			rd.forward(request, response);
		}
		int casrCrewId = 0;
		if (validationFunction.isNullOrEmpty(request.getParameter("casrCrewId"))) {
			throw new ValidationException("validErrorCastCrewID", "Please provide Cast-Crew ID!");
		} else {
			casrCrewId = Integer.parseInt(request.getParameter("casrCrewId"));
		}

		try {
			castCrewDAO.deleteCastCrew(casrCrewId);
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
			request.setAttribute(Constant.ERROR_TEXTMSG, "Cast-Crew " + Constant.SUCCESS_TEXT_DELETEMSG);
		} catch (DBException e) {
			request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
			request.setAttribute(Constant.ERROR_TEXTMSG, Constant.ERROR_OCCURS_OPSMSG);
		}
		RequestDispatcher rd = request.getRequestDispatcher("CastCrew");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
