package com.narola.bookmyseat.castcrew;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import com.narola.bookmyseat.DTO.CastCrewDTO;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;

public class InsertUpdateCCServletFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		String txtCastCrewId = request.getParameter("txtCastCrewId");
		String txtName = request.getParameter("txtName");
		Part txtPimg = httpReq.getPart("txtPImg");
		String DisplayProfileImg = request.getParameter("txtDisImg");
		
		CastCrewDTO castCrewDTO = new CastCrewDTO();
		castCrewDTO.setCastCrewId(txtCastCrewId);
		castCrewDTO.setCastCrewName(txtName);
		castCrewDTO.setCastCrewProfile(txtPimg);
		
		try {
			CastCrewValidator.validate(httpReq, castCrewDTO);
		} catch (ValidationException e) {
			httpReq.setAttribute("txtCastCrewId", txtCastCrewId);
			httpReq.setAttribute("txtName", txtName);
			httpReq.setAttribute("txtDisImg", DisplayProfileImg);

			httpReq.setAttribute(e.getField(), e.getMessage());

			if (httpReq.getRequestURI().equals(httpReq.getContextPath() + Constant.URL_CASTCREW_INSERT)) {
				httpReq.setAttribute("validErrorCCInsert",Constant.ERROR_OCCURS_YES);
			} else if (httpReq.getRequestURI().equals(httpReq.getContextPath() + Constant.URL_CASTCREW_UPDATE)) {
				httpReq.setAttribute("validErrorCCUpdate",Constant.ERROR_OCCURS_YES);
			}
			RequestDispatcher rd = httpReq.getRequestDispatcher("CastCrew");
			rd.forward(httpReq, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			httpReq.setAttribute(Constant.ERROR_OCCURS,Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = httpReq.getRequestDispatcher("CastCrew");
			rd.forward(httpReq, response);
			return;
		}		
		request.setAttribute("castCrewDTO", castCrewDTO);
		chain.doFilter(request, response);
	}
}
