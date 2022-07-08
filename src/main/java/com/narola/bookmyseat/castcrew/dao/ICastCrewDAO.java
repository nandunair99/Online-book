package com.narola.bookmyseat.castcrew.dao;

import java.util.List;

import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.exception.DBException;

public interface ICastCrewDAO {
	public String fetchCastCrewByName(String name) throws DBException;
	public int insertCastCrew(CastCrew castcrew) throws DBException;
	public List<CastCrew> fetchAllCastCrew() throws DBException;
	public CastCrew fetchCastCrewById(int castCrewId) throws DBException;
	public int deleteCastCrew(int id) throws DBException;
	public int updateCastCrew(CastCrew castCrew) throws DBException;
}
