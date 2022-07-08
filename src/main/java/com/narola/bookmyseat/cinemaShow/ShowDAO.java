package com.narola.bookmyseat.cinemaShow;
import com.narola.bookmyseat.entity.Show;
import com.narola.bookmyseat.entity.ShowPrice;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ShowDAO {
	//fetch show data
	public static List<Show> fetchShowDataByCinemaID(int cinemaID){
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		List<Show> al = new ArrayList<>();
		try{
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblshow where screenID in (select screenID from tblscreen where cinemasID = ?)");
			ps.setInt(1, cinemaID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Show show = new Show();
				show.setShowID(rs.getInt("showID"));
				show.setMovieID(rs.getInt("movieID"));
				show.setShowDate(rs.getDate("showDate"));
				show.setShowTime(rs.getString("showTime"));
				show.setEndTime(rs.getString("endTime"));
				show.setScreenID(rs.getInt("screenID"));
				
				ps2 = cn.prepareStatement("select * from tblshowprice where showID=?");
				ps2.setInt(1, rs.getInt("showID"));
				rs2 = ps2.executeQuery();
				List<ShowPrice> sal = new ArrayList<>();
				while (rs2.next()) {
					ShowPrice showprice = new ShowPrice();
					showprice.setShowPriceID(rs2.getInt("showPriceID"));
					showprice.setShowID(rs2.getInt("showID"));
					showprice.setSeatTypeID(rs2.getInt("seatTypeID"));
					showprice.setPrice(rs2.getDouble("price"));
					sal.add(showprice);
				}
				show.setShowPrice(sal);
				al.add(show);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
			DBConnector.getInstance().releaseResource(ps2, rs2);
		}
	}
	//fetch show data by show id
	public static Show fetchShowDataByID(int showID){
		PreparedStatement ps = null;
		ResultSet rs = null;
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		Show show = new Show();
		try{
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblshow where showID = ?");
			ps.setInt(1, showID);
			rs = ps.executeQuery();
			while (rs.next()) {
				show.setShowID(rs.getInt("showID"));
				show.setMovieID(rs.getInt("movieID"));
				show.setShowDate(rs.getDate("showDate"));
				show.setShowTime(rs.getString("showTime"));
				show.setEndTime(rs.getString("endTime"));
				show.setScreenID(rs.getInt("screenID"));
				
				ps2 = cn.prepareStatement("select * from tblshowprice where showID=?");
				ps2.setInt(1, rs.getInt("showID"));
				rs2 = ps2.executeQuery();
				List<ShowPrice> sal = new ArrayList<>();
				while (rs2.next()) {
					ShowPrice showprice = new ShowPrice();
					showprice.setShowPriceID(rs2.getInt("showPriceID"));
					showprice.setShowID(rs2.getInt("showID"));
					showprice.setSeatTypeID(rs2.getInt("seatTypeID"));
					showprice.setPrice(rs2.getDouble("price"));
					sal.add(showprice);
				}
				show.setShowPrice(sal);
			}
			return show;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
			DBConnector.getInstance().releaseResource(ps2, rs2);
		}
	}
	//insert show
	public static int insertShow(Show show) {
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet res = null;
		ResultSet res2 = null;
		Connection cn = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			cn.setAutoCommit(false);
			
			ps = cn.prepareStatement("insert into tblshow (movieID,showDate,showTime,endTime,screenID,createdTime,updatedTime) values (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, show.getMovieID());
			ps.setDate(2, show.getShowDate());
			ps.setString(3, show.getShowTime());
			ps.setString(4, show.getEndTime());
			ps.setInt(5, show.getScreenID());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			res = ps.getGeneratedKeys();
			res.next();
			int showID = res.getInt(1);
			
			for (ShowPrice sp : show.getShowPrice()) {
				ps2 = cn.prepareStatement("insert into tblshowprice (showID,seatTypeID,price,createdTime,updatedTime) values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps2.setInt(1,showID);
				ps2.setInt(2,sp.getSeatTypeID());
				ps2.setDouble(3,sp.getPrice());
				ps2.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				ps2.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				ps2.executeUpdate();
			}
		
			cn.commit();
			return 1;
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DBException("Error while inserting show...", e);
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
	//update show
	public static int updateShow(Show show)
	{
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		Connection cn = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			cn.setAutoCommit(false);
			ps = cn.prepareStatement("update tblshow set movieID=?,showDate=?,showTime=?,endTime=?,screenID=?,updatedTime=? where showID=?");
			ps.setInt(1, show.getMovieID());
			ps.setDate(2, show.getShowDate());
			ps.setString(3, show.getShowTime());
			ps.setString(4, show.getEndTime());
			ps.setInt(5, show.getScreenID());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setInt(7, show.getShowID());
			ps.executeUpdate();
			
			Show temp = ShowDAO.fetchShowDataByID(show.getScreenID());
			 
			if(temp.getScreenID() == show.getScreenID())
			{
				for (ShowPrice sp : show.getShowPrice()) {
					ps2 = cn.prepareStatement("update tblshowprice set price=?,updatedTime=? where showPriceID = ?");
					ps2.setDouble(1, sp.getPrice());
					ps2.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
					ps2.setInt(3, sp.getShowPriceID());
					ps2.executeUpdate();
				}
			}else {
				ps2 = cn.prepareStatement("delete from tblshowprice where showID=?");
				ps2.setInt(1, show.getShowID());
				ps2.executeUpdate();
				
				for (ShowPrice sp : show.getShowPrice()) {
					ps3 = cn.prepareStatement("insert into tblshowprice (showID,seatTypeID,price,createdTime,updatedTime) values (?,?,?,?,?)");
					ps3.setInt(1,show.getShowID());
					ps3.setInt(2,sp.getSeatTypeID());
					ps3.setDouble(3,sp.getPrice());
					ps3.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
					ps3.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
					ps3.executeUpdate();
				}
			}
			cn.commit();
			return 1;
		}catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Error while update show...", e);
		} finally {
			try {
				cn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnector.getInstance().releaseResource(ps);
			DBConnector.getInstance().releaseResource(ps2);
			DBConnector.getInstance().releaseResource(ps3);
		}
	}
}
