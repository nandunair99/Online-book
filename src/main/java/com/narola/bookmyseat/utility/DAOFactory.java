package com.narola.bookmyseat.utility;

import com.narola.bookmyseat.castcrew.dao.CastCrewDAOPostgreas;
import com.narola.bookmyseat.castcrew.dao.CastCrewDAOSQL;
import com.narola.bookmyseat.castcrew.dao.ICastCrewDAO;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.location.dao.LocationDAOPostgreas;
import com.narola.bookmyseat.location.dao.LocationDAOSQL;
import com.narola.bookmyseat.movies.dao.IMovieDAO;
import com.narola.bookmyseat.movies.dao.MovieDAOPostgreas;
import com.narola.bookmyseat.movies.dao.MovieDAOSQL;

public class DAOFactory {
	private static DAOFactory DAO_HELPER = null;
	private ILocationDAO locationDAO = null;
	private IMovieDAO movieDAO = null;
	private ICastCrewDAO castCrewDAO = null;


	public static DAOFactory getInstance() {
		if (DAO_HELPER == null) {
			DAO_HELPER = new DAOFactory();
		}
		return DAO_HELPER;
	}

	public ILocationDAO getlocationDAO() {
		return locationDAO;
	}

	public IMovieDAO getmovieDAO() {
		return movieDAO;
	}

	public ICastCrewDAO getcastcrewDAO() {
		return castCrewDAO;
	}

	public void init(DatabaseType daoType) throws Exception {
		switch (daoType) {
		case MYSQL:
			movieDAO = new MovieDAOSQL();
			locationDAO = new LocationDAOSQL();
			castCrewDAO = new CastCrewDAOSQL();
			break;
		case POSTGRES:
			movieDAO = new MovieDAOPostgreas();
			locationDAO = new LocationDAOPostgreas();
			castCrewDAO = new CastCrewDAOPostgreas();
			break;
		default:
			throw new Exception("Type is not supported yet");
		}
	}
}
