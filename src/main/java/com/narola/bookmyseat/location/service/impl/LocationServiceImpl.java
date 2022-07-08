package com.narola.bookmyseat.location.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.GetPlaces;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.location.dao.ILocationDAO;
import com.narola.bookmyseat.location.service.ILocationService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements ILocationService {

	@Autowired
	private ILocationDAO locationDAO;
	/*public LocationServiceImpl(ILocationDAO locationDAO) {
		this.locationDAO = locationDAO;
	}*/

	@Override
	public void insertUpdateCity(City city, HttpServletRequest request) throws ApplicationException {
		try {
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CITY_INSERT)) {
				locationDAO.insertCity(city);

			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CITY_UPDATE)) {
				city.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
				city.setCityId(city.getCityId());
				locationDAO.updateCity(city);
			}
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public void insertUpdateState(State state, HttpServletRequest request) throws ApplicationException {

		try {
			if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_INSERT)) {
				state.setCreatedTime(new Timestamp(System.currentTimeMillis()));
				state.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
				locationDAO.insertState(state);
			} else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_UPDATE)) {
				state.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
				state.setStateId(state.getStateId());
				locationDAO.updateState(state);
			}
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public GetPlaces getAllPlace() throws ApplicationException {

		try {
			GetPlaces getPlaces = new GetPlaces();

			List<State> stateData = locationDAO.fetchAllState();

			List<City> cityData = locationDAO.fetchAllCity();

			getPlaces.setCityData(cityData);
			getPlaces.setStateData(stateData);
			return getPlaces;

		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public void deleteCity(int cityID) throws ApplicationException {

		try {
			locationDAO.deleteCity(cityID);
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}

	@Override
	public void deleteState(int stateId) throws ApplicationException {

		try {
			locationDAO.deleteState(stateId);
		} catch (DBException e) {
			throw new ApplicationException(Constant.ERROR_OCCURS_OPSMSG, e);
		}
	}
}
