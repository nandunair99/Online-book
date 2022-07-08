package com.narola.bookmyseat.location.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;
import org.springframework.stereotype.Repository;


public class LocationDAOPostgreas implements ILocationDAO{
	// Insert State
	public int insertState(State state) throws DBException {
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("insert into tblstates (name,country_ID,createdTime,updatedTime) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, state.getStateName());
			ps.setInt(2, state.getCountryId());
			ps.setTimestamp(3, state.getCreatedTime());
			ps.setTimestamp(4, state.getUpdatedTime());
			ps.executeUpdate();
			res = ps.getGeneratedKeys();
			res.next();
			return res.getInt(1);
		} catch (SQLException e) {
			throw new DBException("Error while inserting state...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, res);
		}
	}

	// Update State
	public int updateState(State state) throws DBException {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("update tblstates set name=?, updatedTime=? where state_ID=?");
			ps.setString(1, state.getStateName());
			ps.setTimestamp(2, state.getUpdatedTime());
			ps.setInt(3, state.getStateId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while updating state...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}

	// Fetch State
	public List<State> fetchAllState() throws DBException {
		PreparedStatement ps = null;
		ResultSet res = null;
		List<State> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblstates");
			res = ps.executeQuery();
			while (res.next()) {
				State state = new State();
				state.setStateId(res.getInt("state_ID"));
				state.setStateName(res.getString("name"));
				state.setCountryId(res.getInt("country_ID"));
				state.setCreatedTime(res.getTimestamp("createdTime"));
				state.setUpdatedTime(res.getTimestamp("updatedTime"));
				al.add(state);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching state...", e);
		} catch (DBException e) {
			throw e;
		}finally {
			DBConnector.getInstance().releaseResource(ps, res);
		}
	}

	// fetch State with name
	public String fetchStateByName(String state) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select name from tblstates where name=?");
			ps.setString(1, state);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException("Error while searching state...", e);
		} catch (DBException e) {
			throw e;
		}finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	/***
	 * Seacrh State with state name 
	 * @param txtstate
	 * 		  State name
	 * @return
	 * 	      Return a all data of state which name is match to parameter name.
	 * @throws DBException
	 * 		   Throw a error if database related issue occur.
	 */
	public List<State> searchStateByName(String txtstate) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<State> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblstates where name like ?");
			ps.setString(1, "%" + txtstate + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				State state = new State();
				state.setStateId(rs.getInt("state_ID"));
				state.setStateName(rs.getString("name"));
				state.setCountryId(rs.getInt("country_ID"));
				state.setCreatedTime(rs.getTimestamp("createdTime"));
				state.setUpdatedTime(rs.getTimestamp("updatedTime"));
				al.add(state);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while searching state...", e);
		} catch (DBException e) {
			throw e;
		}finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	// Delete State
	public int deleteState(int id) throws DBException {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("delete from tblstates where state_ID=?");
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while deleting state...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}
	// Insert City
	public int insertCity(City city) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement(
					"insert into tblcity (name,state_ID,createdTime,updatedTime) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, city.getCityName());
			ps.setInt(2, city.getStateId());
			ps.setTimestamp(3, city.getCreatedTime());
			ps.setTimestamp(4, city.getUpdatedTime());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new DBException("Error while inserting city...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	// Update City
	public int updateCity(City city) throws DBException {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("update tblcity set name=?, state_ID=?, updatedTime=? where city_ID=?");
			ps.setString(1, city.getCityName());
			ps.setInt(2, city.getStateId());
			ps.setTimestamp(3, city.getUpdatedTime());
			ps.setInt(4, city.getCityId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while updating city...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}

	// Fetch City
	public List<City> fetchAllCity() throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection cn = DBConnector.getInstance().getConnection();
		List<City> al = new ArrayList<City>();
		try {
			ps = cn.prepareStatement("select * from tblcity");
			rs = ps.executeQuery();
			while (rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("city_ID"));
				city.setCityName(rs.getString("name"));
				city.setStateId(rs.getInt("state_ID"));
				city.setCreatedTime(rs.getTimestamp("createdTime"));
				city.setUpdatedTime(rs.getTimestamp("updatedTime"));
				al.add(city);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching city...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	/**
	 * Delete city by city id
	 * 
	 * @param id City id name to delete
	 * 
	 * @throws DBException In case of Database related issues.
	 * 
	 */
	public int deleteCity(int id) throws DBException {
		PreparedStatement ps = null;
		Connection cn = DBConnector.getInstance().getConnection();
		try {
			ps = cn.prepareStatement("delete from tblcity where city_ID=?");
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while deleting city...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}

	/**
	 * Get City by city name and state id
	 * 
	 * @param city City name to search
	 * @param sid  State ID
	 * 
	 * @throws DBException In case of Database related issues.
	 * 
	 */
	public String fetchCityByNameWithState(String city, int sid) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select name from tblcity where name=? and state_ID=?");
			ps.setString(1, city);
			ps.setInt(2, sid);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException("Error while searching city.", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
}

