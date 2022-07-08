package com.narola.bookmyseat.castcrew;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.narola.bookmyseat.DTO.CastCrewDTO;
import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;

public class InsertUpdateCCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ICastCrewDAO castCrewDAO = DAOFactory.getInstance().getcastcrewDAO();
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

		CastCrewDTO castCrewDTO = new CastCrewDTO();
		castCrewDTO = (CastCrewDTO) request.getAttribute("castCrewDTO");
		InputStream filecontent = null;

		if (castCrewDTO.getCastCrewProfile().getSize() != 0) {
			filecontent = castCrewDTO.getCastCrewProfile().getInputStream();
		}
		CastCrew castcrew = new CastCrew();
		castcrew.setCastCrewName(castCrewDTO.getCastCrewName());
		castcrew.setProfileImgStream(filecontent);
		castcrew.setCreatedTime(new Timestamp(System.currentTimeMillis()));
		castcrew.setUpdatedTime(new Timestamp(System.currentTimeMillis()));

		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CASTCREW_INSERT)) {
			try {
				castCrewDAO.insertCastCrew(castcrew);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cast-Crew " + Constant.SUCCESS_TEXT_INSERTMSG);
			} catch (Exception e) {
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cast-Crew " + Constant.ERROR_TEXT_OPRATIONFAIL);
			}
		} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CASTCREW_UPDATE)) {
			castcrew.setCastCrewId(Integer.parseInt(castCrewDTO.getCastCrewId()));
			try {
				castCrewDAO.updateCastCrew(castcrew);
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cast-Crew " + Constant.SUCCESS_TEXT_UPDATEMSG);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_YES);
				request.setAttribute(Constant.ERROR_TEXTMSG, "Cast-Crew " + Constant.ERROR_TEXT_OPRATIONFAIL);
				return;
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("CastCrew");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
