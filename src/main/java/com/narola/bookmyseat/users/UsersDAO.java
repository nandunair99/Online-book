package com.narola.bookmyseat.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.Address;
import com.narola.bookmyseat.entity.CinemaAdmin;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;
import com.narola.bookmyseat.utility.validationFunction;

public class UsersDAO {	
	public static List<User> fetchAllUsers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> al =  new ArrayList<>();
		try{
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select u.*,a.addressID,a.addressline1,a.addressline2,a.landmark,a.city_ID,a.pincode from tbluser as u,tbladdress as a where u.usertype != 0 and u.addressID=a.addressID");
			rs = ps.executeQuery();
			while (rs.next()) {
				User userdata =  new User();
				userdata.setUserID(rs.getInt("userID"));
				userdata.setFirstName(rs.getString("firstname"));
				userdata.setLastName(rs.getString("lastname"));
				userdata.setEmailID(rs.getString("email"));
				userdata.setPassword(rs.getString("password"));
				userdata.setPhoneno(rs.getString("phone"));
				userdata.setAddressID(rs.getInt("addressID"));
				userdata.setUserTyp(rs.getInt("usertype"));
				userdata.setProfileImg(rs.getBlob("profileImg").getBinaryStream());
				
				Address address = new Address();
				address.setAddressID(rs.getInt("addressID"));
				address.setAddressline1(rs.getString("addressline1"));
				address.setAddressline2(rs.getString("addressline2"));
				address.setLandmark(rs.getString("landmark"));
				address.setCity_ID(rs.getInt("city_ID"));
				address.setPincode(rs.getInt("pincode"));
				userdata.setAddressData(address);
				
				al.add(userdata);
			}
			return al;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Error while fetching all user...", e);
		} catch (DBException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	public static int insertUser(User user,Address address,int cinemaID)
	{
		PreparedStatement ps = null;
		ResultSet res = null;
		PreparedStatement ps2 = null;
		ResultSet res2 = null;
		PreparedStatement ps3 = null;
		
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
			
			
			ps2 = cn.prepareStatement("insert into tbluser (firstname,lastname,email,password,phone,addressID,usertype,profileImg,createdTime,updatedTime) values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps2.setString(1, user.getFirstName());
			ps2.setString(2, user.getLastName());
			ps2.setString(3, user.getEmailID());
			ps2.setString(4, String.valueOf(validationFunction.generatePassword(8)));
			ps2.setString(5, user.getPhoneno());
			ps2.setInt(6, addressId);
			ps2.setInt(7, user.getUserTyp());
			ps2.setBlob(8, user.getProfileImg());
			ps2.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			ps2.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
			
			ps2.executeUpdate();
			res2 = ps2.getGeneratedKeys();
			res2.next();
			int userID = res2.getInt(1);
			
			ps3 = cn.prepareStatement("insert into tblcinemaadmin (userID,cinemasID) values (?,?)");
			ps3.setInt(1, userID);
			ps3.setInt(2, cinemaID);
			ps3.executeUpdate();
			
			cn.commit();
			return 1;
		}catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Error while inserting user...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			try {
				cn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnector.getInstance().releaseResource(ps, res);
			DBConnector.getInstance().releaseResource(ps2);
			DBConnector.getInstance().releaseResource(ps3);
		}
	}
	public static int insertUser(User user,Address address)
	{
		PreparedStatement ps = null;
		ResultSet res = null;
		PreparedStatement ps2 = null;
		
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
			
			
			ps2 = cn.prepareStatement("insert into tbluser (firstname,lastname,email,password,phone,addressID,usertype,profileImg,createdTime,updatedTime) values (?,?,?,?,?,?,?,?,?,?)");
			ps2.setString(1, user.getFirstName());
			ps2.setString(2, user.getLastName());
			ps2.setString(3, user.getEmailID());
			ps2.setString(4, String.valueOf(validationFunction.generatePassword(8)));
			ps2.setString(5, user.getPhoneno());
			ps2.setInt(6, addressId);
			ps2.setInt(7, user.getUserTyp());
			ps2.setBlob(8, user.getProfileImg());
			ps2.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			ps2.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
			ps2.executeUpdate();
			
			cn.commit();
			return 1;
		}catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Error while inserting user...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			try {
				cn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnector.getInstance().releaseResource(ps, res);
			DBConnector.getInstance().releaseResource(ps2);

		}
	}
	//fetch cinema admin
	public static List<CinemaAdmin> fetchCinemaAdmin() throws DBException {
		PreparedStatement ps = null;
		ResultSet res = null;
		List<CinemaAdmin> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblcinemaadmin");
			res = ps.executeQuery();
			while (res.next()) {
				CinemaAdmin admin = new CinemaAdmin();
				admin.setUserID(res.getInt("userID"));
				admin.setCinemaID(res.getInt("cinemasID"));
				al.add(admin);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching tblcinemaadmin...", e);
		} catch (DBException e) {
			throw e;
		}finally {
			DBConnector.getInstance().releaseResource(ps, res);
		}
	}
}
