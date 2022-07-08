package com.narola.bookmyseat.cinemaShow;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.DTO.ShowDTO;
import com.narola.bookmyseat.entity.Show;
import com.narola.bookmyseat.exception.ValidationException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.validationFunction;

public class ShowValidator {
	public static void validate(ShowDTO showDTO)throws ValidationException {
		
		List<Show> ShowData = ShowDAO.fetchShowDataByCinemaID(1);
		
		if (validationFunction.isNullOrEmpty(showDTO.getMovieID())) {
			System.out.println(showDTO.getMovieID());
			throw new ValidationException("validErrorMovieID", "Please select movie!");
		}
		if (validationFunction.isNullOrEmpty(showDTO.getShowDate())) {
			System.out.println(showDTO.getShowDate());
			throw new ValidationException("validErrorShowDate", "Please select show date!");
		}
		if (validationFunction.isNullOrEmpty(showDTO.getShowTime())) {
			System.out.println(showDTO.getShowTime());
			throw new ValidationException("validErrorShowTime", "Please select show time!");
		}
		if (validationFunction.isNullOrEmpty(showDTO.getScreenID())) {
			System.out.println(showDTO.getScreenID());
			throw new ValidationException("validErrorScreenID", "Please select screen!");
		}
		if (showDTO.getSeatTypeID().length != showDTO.getSeatTypePrice().length) {
			System.out.println(showDTO.getSeatTypePrice());
			throw new ValidationException("validErrorShowPrice", "Please enter price!");
		}
		for (Show show : ShowData) {
			if(show.getShowDate().compareTo(Date.valueOf(showDTO.getShowDate()))== 0 && show.getScreenID() == Integer.parseInt(showDTO.getScreenID()) )
			{
				if( LocalTime.parse(showDTO.getShowTime()).isBefore(LocalTime.parse(show.getShowTime()))) {
					if(LocalTime.parse(showDTO.getEndTime()).isBefore(LocalTime.parse(show.getShowTime()))) {
						continue;
					}else {
						throw new ValidationException("validErrorSameTime", "Please check again for duplicated showtimes!");
					}
				}else {
					if(LocalTime.parse(showDTO.getShowTime()).isAfter(LocalTime.parse(show.getEndTime())))
					{
						continue;
					}else {
						throw new ValidationException("validErrorSameTime", "Please check again for duplicated showtimes!");
					}
				}
			}
		}
		if(LocalTime.parse(showDTO.getShowTime()).isAfter(LocalTime.parse("23:00")) || LocalTime.parse(showDTO.getShowTime()).isBefore(LocalTime.parse("08:00"))) {
			throw new ValidationException("validErrorSameTime", "Please check again for invalid showtimes!");
		}
	}
	public static void validate(HttpServletRequest request, ShowDTO showDTO)throws ValidationException {
		validate(showDTO);
		List<Show> ShowData = ShowDAO.fetchShowDataByCinemaID(1);
		if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_SHOW_UPDATE)) {
			if (validationFunction.isNullOrEmpty(showDTO.getShowID())) {
				throw new ValidationException("validErrorShowID", "Please provide Show ID");
			}
			for (Show show : ShowData) {
				if(show.getShowDate().compareTo(Date.valueOf(showDTO.getShowDate()))== 0 && show.getScreenID() == Integer.parseInt(showDTO.getScreenID()) && show.getShowID() != Integer.parseInt(showDTO.getShowID()) )
				{
					if( LocalTime.parse(showDTO.getShowTime()).isBefore(LocalTime.parse(show.getShowTime()))) {
						if(LocalTime.parse(showDTO.getEndTime()).isBefore(LocalTime.parse(show.getShowTime()))) {
							continue;
						}else {
							throw new ValidationException("validErrorSameTime", "Please check again for duplicated showtimes!");
						}
					}else {
						if(LocalTime.parse(showDTO.getShowTime()).isAfter(LocalTime.parse(show.getEndTime())))
						{
							continue;
						}else {
							throw new ValidationException("validErrorSameTime", "Please check again for duplicated showtimes!");
						}
					}
				}
			}
		}
		
	}
}
