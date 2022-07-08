package com.narola.bookmyseat.cinemas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.Address;
import com.narola.bookmyseat.entity.Cinemas;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;

public class CinemasDAO {
		// Insert Cinema
		public static int insertCinema(Cinemas cinemas,Address address) throws DBException {
			PreparedStatement ps = null;
			ResultSet res = null;
			PreparedStatement ps2 = null;
			ResultSet res2 = null;
			Connection cn = null;
			try {
				cn = DBConnector.getInstance().getConnection();
				cn.setAutoCommit(false);
				
				ps = cn.prepareStatement("insert into tbladdress (addressline1,addressline2,landmark,city_ID,pincode,createdTime,updatedTime) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, address.getAddressline1());
				ps.setString(2, address.getAddressline2());
				ps.setString(3, address.getLandmark());
				ps.setInt(4, address.getCity_ID());
				ps.setInt(5, address.getPincode());
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();
				res = ps.getGeneratedKeys();
				res.next();
				int addressId = res.getInt(1);
				
				ps2 = cn.prepareStatement("insert into tblcinemas (name,addressID,facilities,status,createdTime,updatedTime) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps2.setString(1, cinemas.getName());
				ps2.setInt(2,addressId);
				ps2.setString(3, cinemas.getFacilities());
				ps2.setInt(4,cinemas.getStatus());
				ps2.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				ps2.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				
				ps2.executeUpdate();
				res2 = ps2.getGeneratedKeys();
				res2.next();
				cn.commit();
				return res2.getInt(1);
			} catch (SQLException e) {
				try {
					cn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new DBException("Error while inserting cinema...", e);
			} catch (DBException e) {
				throw e;
			} finally {
				try {
					cn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBConnector.getInstance().releaseResource(ps, res);
				DBConnector.getInstance().releaseResource(ps2, res2);
			}
		}
		//update cinema
		
		public static int updateCinema(Cinemas cinemas,Address address) throws DBException {
			PreparedStatement ps = null;
			PreparedStatement ps2 = null;
			Connection cn = null;
			try {
				cn = DBConnector.getInstance().getConnection();
				cn.setAutoCommit(false);
				
				ps = cn.prepareStatement("update tbladdress set addressline1= ?,addressline2=?,landmark=?,city_ID=?,pincode=?,updatedTime=? where addressID=?");
				ps.setString(1, address.getAddressline1());
				ps.setString(2, address.getAddressline2());
				ps.setString(3, address.getLandmark());
				ps.setInt(4, address.getCity_ID());
				ps.setInt(5, address.getPincode());
				ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
				ps.setInt(7, address.getAddressID());
				ps.executeUpdate();
	
				ps2 = cn.prepareStatement("update tblcinemas set name=?,addressID=?,facilities=?,status=?,updatedTime=? where cinemasID=?");
				ps2.setString(1, cinemas.getName());
				ps2.setInt(2,cinemas.getAddressID());
				ps2.setString(3, cinemas.getFacilities());
				ps2.setInt(4,cinemas.getStatus());
				ps2.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				ps2.setInt(6,cinemas.getCinemasID());
				ps2.executeUpdate();
				cn.commit();
				return 1;
			} catch (SQLException e) {
				try {
					cn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new DBException("Error while inserting cinema...", e);
			} catch (DBException e) {
				throw e;
			} finally {
				try {
					cn.setAutoCommit(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBConnector.getInstance().releaseResource(ps);
				DBConnector.getInstance().releaseResource(ps2);
			}
		}
		
		//fetch cinema
		public static List<Cinemas> fetchAllCinemas() throws DBException{
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<Cinemas> al = new ArrayList<>();
			try {
				Connection cn = DBConnector.getInstance().getConnection();
				ps = cn.prepareStatement("select c.cinemasID,c.name,c.addressID,c.facilities,c.status,a.addressID,a.addressline1,a.addressline2,a.landmark,a.city_ID,a.pincode from tblcinemas as c,tbladdress as a where c.addressID=a.addressID");
				rs = ps.executeQuery();
				while (rs.next()) {
					Cinemas cinema = new Cinemas();
					Address address = new Address();
					cinema.setCinemasID(rs.getInt("cinemasID"));
					cinema.setName(rs.getString("name"));
					cinema.setAddressID(rs.getInt("addressID"));
					cinema.setFacilities(rs.getString("facilities"));
					cinema.setStatus(rs.getInt("status"));
					
					address.setAddressID(rs.getInt("addressID"));
					address.setAddressline1(rs.getString("addressline1"));
					address.setAddressline2(rs.getString("addressline2"));
					address.setLandmark(rs.getString("landmark"));
					address.setCity_ID(rs.getInt("city_ID"));
					address.setPincode(rs.getInt("pincode"));
					cinema.setAddressData(address);
					al.add(cinema);
				}
				return al;
			} catch (SQLException e) {
				throw new DBException("Error while fetching cinema...", e);
			} catch (DBException e) {
				throw e;
			} finally {
				DBConnector.getInstance().releaseResource(ps, rs);
			}
		}	
		//Fetch cinema by id
		public static Cinemas fetchAllCinemasByID(int cinemaId) throws DBException{
			PreparedStatement ps = null;
			ResultSet rs = null;
			Cinemas cinema = new Cinemas();
			try {
				Connection cn = DBConnector.getInstance().getConnection();
				ps = cn.prepareStatement("select c.cinemasID,c.name,c.addressID,c.facilities,c.status,a.addressID,a.addressline1,a.addressline2,a.landmark,a.city_ID,a.pincode from tblcinemas as c,tbladdress as a where c.addressID=a.addressID and cinemasID=?");
				ps.setInt(1, cinemaId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Address address = new Address();
					cinema.setCinemasID(rs.getInt("cinemasID"));
					cinema.setName(rs.getString("name"));
					cinema.setAddressID(rs.getInt("addressID"));
					cinema.setFacilities(rs.getString("facilities"));
					cinema.setStatus(rs.getInt("status"));
					
					address.setAddressID(rs.getInt("addressID"));
					address.setAddressline1(rs.getString("addressline1"));
					address.setAddressline2(rs.getString("addressline2"));
					address.setLandmark(rs.getString("landmark"));
					address.setCity_ID(rs.getInt("city_ID"));
					address.setPincode(rs.getInt("pincode"));
					cinema.setAddressData(address);
				}
				return cinema;
			} catch (SQLException e) {
				throw new DBException("Error while fetching cinema...", e);
			} catch (DBException e) {
				throw e;
			} finally {
				DBConnector.getInstance().releaseResource(ps, rs);
			}
		}	
}
