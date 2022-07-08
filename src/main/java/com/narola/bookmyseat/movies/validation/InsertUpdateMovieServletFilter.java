package com.narola.bookmyseat.movies.validation;

import java.io.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.narola.bookmyseat.DTO.MovieDTO;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;

public class InsertUpdateMovieServletFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		/*String varMovieId = request.getParameter("txtMovieId");
		String varMovieName = request.getParameter("txtMovieName");
		String varReleaseDate = request.getParameter("txtReleaseDate");
		String varDuration = request.getParameter("txtDuration");
		String varCensorRating = request.getParameter("txtCensorRating");
		String varGenre[] =  request.getParameterValues("txtGenre");
		String varLanguage[] = request.getParameterValues("txtLanguage");
		Part varMovieBanner = httpReq.getPart("txtMovieBanner");
		Part varMoviePoster = httpReq.getPart("txtMoviePoster");
		String varType[] = request.getParameterValues("txtType");
		String varCastCrewId[] = request.getParameterValues("txtCastCrewId");
		String varCastCrewName[] = request.getParameterValues("txtcastCrewName");
		String varMovieDescription = request.getParameter("txtMovieDescription");
		String varMoviePsterImg = request.getParameter("txtMoviePosterImg");
		String varMovieBannerImg = request.getParameter("txtMovieBannerImg");
		String varStatus =  request.getParameter("txtStatus");
		String vargenreCreatedTime = request.getParameter("genreCreatedTime");
		String varlanguageCreatedTime = request.getParameter("languageCreatedTime");
		String varcastCrewCreatedTime = request.getParameter("castCrewCreatedTime");
		
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setMovieId(varMovieId);
		movieDTO.setMovieName(varMovieName);
		movieDTO.setReleaseDate(varReleaseDate);
		movieDTO.setDuration(varDuration);
		movieDTO.setCensorRating(varCensorRating);
		movieDTO.setGenre(varGenre); //array
		movieDTO.setLanguage(varLanguage); //array
		movieDTO.setMovieBanner(varMovieBanner);
		movieDTO.setMoviePoster(varMoviePoster);
		movieDTO.setType(varType); //array
		movieDTO.setCastCrewId(varCastCrewId); //array
		movieDTO.setMovieDescription(varMovieDescription);
		movieDTO.setStatus(varStatus);
		movieDTO.setCastCrewCreatedTime(varcastCrewCreatedTime);
		movieDTO.setGenreCreatedTime(vargenreCreatedTime);
		movieDTO.setLanguageCreatedTime(varlanguageCreatedTime);
		
		
		try {
			MovieValidator.validate(httpReq, movieDTO);
		}catch (ValidationException e) {
			httpReq.setAttribute("afterMovieData", movieDTO);
			httpReq.setAttribute("txtMoviePosterImg", varMoviePsterImg);
			httpReq.setAttribute("txtMovieBannerImg", varMovieBannerImg);
			httpReq.setAttribute("txtCastCrewName", varCastCrewName);
			httpReq.setAttribute("genreCreatedTime", vargenreCreatedTime);
			httpReq.setAttribute("languageCreatedTime", varlanguageCreatedTime);
			httpReq.setAttribute("castCrewCreatedTime", varcastCrewCreatedTime);
			
			
			httpReq.setAttribute(e.getField(), e.getMessage());
			
			if (httpReq.getRequestURI().equals(httpReq.getContextPath() + Constant.URL_MOVIE_INSERT)) {
				httpReq.setAttribute("validErrorMovieInsert",Constant.ERROR_OCCURS_YES);
			} else if (httpReq.getRequestURI().equals(httpReq.getContextPath() + Constant.URL_MOVIE_UPDATE)) {
				httpReq.setAttribute("validErrorMovieUpdate",Constant.ERROR_OCCURS_YES);
			}
			RequestDispatcher rd = httpReq.getRequestDispatcher("Movies");
			rd.forward(httpReq, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			httpReq.setAttribute(Constant.ERROR_OCCURS,Constant.ERROR_OCCURS_OPSMSG);
			RequestDispatcher rd = httpReq.getRequestDispatcher("Movies");
			rd.forward(httpReq, response);
			return;
		}	
		request.setAttribute("movieDTO", movieDTO);
		chain.doFilter(request, response);*/
	}
	public void init(FilterConfig Config) throws ServletException {
	}
}
