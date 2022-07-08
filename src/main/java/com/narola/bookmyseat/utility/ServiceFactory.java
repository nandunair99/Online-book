package com.narola.bookmyseat.utility;

import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.location.service.ILocationService;
import com.narola.bookmyseat.location.service.impl.LocationServiceImpl;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.movies.service.impl.MovieServiceBuilder;

public class ServiceFactory {
	private static ServiceFactory SERVICE_HELPER = null;
	private ILocationService locationService = null;
	private IMovieService movieService = null;

	public static ServiceFactory getInstance() {
		if (SERVICE_HELPER == null) {
			SERVICE_HELPER = new ServiceFactory();
		}
		return SERVICE_HELPER;
	}

	public ILocationService getlocationService() {
		return locationService;
	}

	public IMovieService getmovieService() {
		return movieService;
	}

	public void init() {
		ILocationDAO locationDAO = DAOFactory.getInstance().getlocationDAO();
		IMovieDAO movieDAO = DAOFactory.getInstance().getmovieDAO();
		ICastCrewDAO castCrewDAO = DAOFactory.getInstance().getcastcrewDAO();

		//locationService = new LocationServiceImpl(locationDAO);
		//movieService = new MovieServiceBuilder().setMovieDAO(movieDAO).setCastCrewDAO(castCrewDAO).build();
	}

}
