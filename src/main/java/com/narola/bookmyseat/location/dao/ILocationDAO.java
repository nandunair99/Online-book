package com.narola.bookmyseat.location.dao;

import java.util.List;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.exception.DBException;

public interface ILocationDAO {
	public int insertState(State state) throws DBException;
	public int updateState(State state) throws DBException;
	public List<State> fetchAllState() throws DBException;
	public String fetchStateByName(String state) throws DBException;
	public List<State> searchStateByName(String txtstate) throws DBException;
	public int deleteState(int id) throws DBException;
	public int insertCity(City city) throws DBException;
	public int updateCity(City city) throws DBException;
	public List<City> fetchAllCity() throws DBException;
	public int deleteCity(int id) throws DBException;
	public String fetchCityByNameWithState(String city, int sid) throws DBException;
	
}
