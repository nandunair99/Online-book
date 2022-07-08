package com.narola.bookmyseat.cinemaScreen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.Screen;
import com.narola.bookmyseat.entity.ScreenSeatType;
import com.narola.bookmyseat.entity.SeatLayout;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;

public class ScreenDAO {
	public static int insertScreen(Screen screen,ScreenSeatType[] seattype) {
		PreparedStatement ps = null;
		ResultSet res = null;
		PreparedStatement ps2 = null;
		ResultSet res2 = null;
		Connection cn = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			cn.setAutoCommit(false);
			
			ps = cn.prepareStatement("insert into tblscreen (name,cinemasID,createdTime,updatedTime) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, screen.getScreenName());
			ps.setInt(2, screen.getCinemaID());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			res = ps.getGeneratedKeys();
			res.next();
			int screenID = res.getInt(1);
			System.out.println("DAO:"+seattype.length);
			for(int i=0;i<seattype.length;i++)
			{
				ps2 = cn.prepareStatement("insert into tblscreenseattype (SeatType,screenID,noofrow,createdTime,updatedTime) values (?,?,?,?,?)");
				ps2.setString(1, seattype[i].getSeatType());
				ps2.setInt(2, screenID);
				ps2.setInt(3, seattype[i].getNoOfRow());
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
			throw new DBException("Error while inserting screen...", e);
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
			DBConnector.getInstance().releaseResource(ps2, res2);
		}
	}
	//fetch screen data only
	public static List<Screen> fetchAllScreenByCinemaID(int cinemasID) throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Screen> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblscreen where cinemasID=?");
			ps.setInt(1, cinemasID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Screen screen = new Screen();
				screen.setScreenID(rs.getInt("screenID"));
				screen.setScreenName(rs.getString("name"));
				screen.setCinemaID(rs.getInt("cinemasID"));
				al.add(screen);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}	
	// fetch screen data by screen id 
	public static Screen fetchScreenById(int screenId) throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		Screen screen = new Screen();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblscreen where screenID=?");
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			while (rs.next()) {
				screen.setScreenID(rs.getInt("screenID"));
				screen.setScreenName(rs.getString("name"));
				screen.setCinemaID(rs.getInt("cinemasID"));
			}
			return screen;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen by screen id...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}	
	//fetch all screen seat type by screen id
	public static List<ScreenSeatType> fetchAllScreenSeatType(int screenId) throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ScreenSeatType> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblscreenseattype where screenID=?");
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			while (rs.next()) {
				ScreenSeatType seatType = new ScreenSeatType();
				seatType.setScreenSeatTypeID(rs.getInt("seatTypeID"));
				seatType.setSeatType(rs.getString("SeatType"));
				seatType.setScreenID(rs.getInt("screenID"));
				seatType.setNoOfRow(rs.getInt("noofrow"));
				al.add(seatType);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen seat type by screen id...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}	
	//fetch all screen seat type
	public static List<ScreenSeatType> fetchAllScreenSeatType() throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ScreenSeatType> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblscreenseattype");
			rs = ps.executeQuery();
			while (rs.next()) {
				ScreenSeatType seatType = new ScreenSeatType();
				seatType.setScreenSeatTypeID(rs.getInt("seatTypeID"));
				seatType.setSeatType(rs.getString("SeatType"));
				seatType.setScreenID(rs.getInt("screenID"));
				seatType.setNoOfRow(rs.getInt("noofrow"));
				al.add(seatType);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen seat type...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}	
	public static int insertSeatLayout(SeatLayout[] seatlayout)
	{
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			for(int i=0;i<seatlayout.length;i++)
			{
				ps = cn.prepareStatement("insert into tblscreenseatlayout (seatNo,seatTypeID,status,createdTime,updatedTime) values (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, seatlayout[i].getSeatNo());
				ps.setInt(2, seatlayout[i].getSeatTypeID());
				ps.setInt(3, seatlayout[i].getStatus());
				ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
				ps.executeUpdate();	
				res = ps.getGeneratedKeys();
				res.next();
			}
			return res.getInt(1);
		}catch (SQLException e) {
			throw new DBException("Error while insert layout...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, res);
		}
	}
	//fetch layout
	public static List<SeatLayout> fetchScreenSeatLayout(int screenId){
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SeatLayout> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from bookmyseat.tblscreenseatlayout where seatTypeID in (SELECT seatTypeID FROM bookmyseat.tblscreenseattype where screenID = ?)");
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			while (rs.next()) {
				SeatLayout seatLayout = new SeatLayout();
				seatLayout.setScreenSeatID(rs.getInt("screenSeatID"));
				seatLayout.setSeatNo(rs.getString("seatNo"));
				seatLayout.setSeatTypeID(rs.getInt("seatTypeID"));
				seatLayout.setStatus(rs.getInt("status"));
				al.add(seatLayout);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen layout...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	public static int checkLayoutCreate(int screenId) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select COUNT(*) as totalCount from tblscreenseattype where seatTypeID in (select seatTypeID from tblscreenseatlayout) and screenID = ?");
			ps.setInt(1, screenId);
			rs = ps.executeQuery();
			rs.next();
			return rs.getInt("totalCount");
		} catch (SQLException e) {
			throw new DBException("Error while fetching screen layout...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	public static int updateScreen(Screen screen) {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("update tblscreen set name=?, updatedTime=? where screenID=?");
			ps.setString(1, screen.getScreenName());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3, screen.getScreenID());
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while updating screen...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}
}
