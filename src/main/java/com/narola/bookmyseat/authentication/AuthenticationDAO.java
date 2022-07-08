package com.narola.bookmyseat.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import com.narola.bookmyseat.entity.Address;
import com.narola.bookmyseat.entity.User;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;
import com.narola.bookmyseat.utility.validationFunction;

public class AuthenticationDAO {
	public static User checkAuth(String email,String password,String type) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		User userdata =  new User();
		try{
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select *,COUNT(*) AS recordCount from tbluser where email=? and password =? and usertype =?",ResultSet.TYPE_SCROLL_INSENSITIVE);
			ps.setString(1, email);
			ps.setString(2, password);
			ps.setInt(3, Integer.parseInt(type));
			rs = ps.executeQuery();
			while (rs.next()) {
					userdata.setUserID(rs.getInt("userID"));
					userdata.setFirstName(rs.getString("firstname"));
					userdata.setLastName(rs.getString("lastname"));
					userdata.setEmailID(rs.getString("email"));
					userdata.setPassword(rs.getString("password"));
					userdata.setPhoneno(rs.getString("phone"));
					userdata.setAddressID(rs.getInt("addressID"));
					userdata.setUserTyp(rs.getInt("usertype"));
					userdata.setProfileImg(rs.getBlob("profileImg").getBinaryStream());
					userdata.setCreatedTime(rs.getTimestamp("createdTime"));
					userdata.setUpdatedTime(rs.getTimestamp("updatedTime"));
			}
			return userdata;
			
		}catch (SQLException e) {
			throw new DBException("Error while fetching user...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
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
			e.printStackTrace();
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
}
