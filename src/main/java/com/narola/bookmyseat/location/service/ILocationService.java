package com.narola.bookmyseat.location.service;

import javax.servlet.http.HttpServletRequest;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.GetPlaces;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.exception.ApplicationException;
import org.springframework.stereotype.Service;

@Service
public interface ILocationService {
	void insertUpdateCity(City city, HttpServletRequest request) throws ApplicationException;

	void insertUpdateState(State state, HttpServletRequest request) throws ApplicationException;

	GetPlaces getAllPlace() throws ApplicationException;

	void deleteCity(int cityID) throws ApplicationException;

	void deleteState(int stateId) throws ApplicationException;
}