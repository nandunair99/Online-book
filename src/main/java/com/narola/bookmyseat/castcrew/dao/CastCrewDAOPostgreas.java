package com.narola.bookmyseat.castcrew.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.CastCrew;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;

public class CastCrewDAOPostgreas implements ICastCrewDAO {
	// fetch CC with name
	public String fetchCastCrewByName(String name) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select name from tblcastcrew where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString(1);
			}
			return null;
		} catch (SQLException e) {
			throw new DBException("Error while searching CC name...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	// Insert CC
	public int insertCastCrew(CastCrew castcrew) throws DBException {
		PreparedStatement ps = null;
		ResultSet res = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement(
					"insert into tblcastcrew (name,profileImg,createdTime,updatedTime) values (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, castcrew.getCastCrewName());
			ps.setBlob(2, castcrew.getProfileImgStream());
			ps.setTimestamp(3, castcrew.getCreatedTime());
			ps.setTimestamp(4, castcrew.getUpdatedTime());
			ps.executeUpdate();
			res = ps.getGeneratedKeys();
			res.next();
			return res.getInt(1);
		} catch (SQLException e) {
			throw new DBException("Error while inserting cast-crew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, res);
		}
	}

	// Fetch CC
	public List<CastCrew> fetchAllCastCrew() throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CastCrew> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblcastcrew");
			rs = ps.executeQuery();
			while (rs.next()) {
				byte[] img = rs.getBytes("profileImg");
				CastCrew castcrew = new CastCrew();
				castcrew.setCastCrewId(rs.getInt("CC_ID"));
				castcrew.setCastCrewName(rs.getString("name"));
				castcrew.setProfileImgStream(rs.getBlob("profileImg").getBinaryStream());
				castcrew.setProfileImg(img);
				castcrew.setCreatedTime(rs.getTimestamp("createdTime"));
				castcrew.setUpdatedTime(rs.getTimestamp("updatedTime"));
				al.add(castcrew);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching cast-crew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	// Fetch CC By Id
	public CastCrew fetchCastCrewById(int castCrewId) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		CastCrew castcrew = new CastCrew();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblcastcrew where CC_ID=?");
			ps.setInt(1, castCrewId);
			rs = ps.executeQuery();
			while (rs.next()) {
				byte[] img = rs.getBytes("profileImg");
				castcrew.setCastCrewId(rs.getInt("CC_ID"));
				castcrew.setCastCrewName(rs.getString("name"));
				castcrew.setProfileImgStream(rs.getBlob("profileImg").getBinaryStream());
				castcrew.setProfileImg(img);
				castcrew.setCreatedTime(rs.getTimestamp("createdTime"));
				castcrew.setUpdatedTime(rs.getTimestamp("updatedTime"));
			}
			return castcrew;
		} catch (SQLException e) {
			throw new DBException("Error while fetching cast-crew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}

	// Delete CC
	public int deleteCastCrew(int id) throws DBException {
		PreparedStatement ps = null;
		Connection cn = DBConnector.getInstance().getConnection();
		try {
			ps = cn.prepareStatement("delete from tblcastcrew where CC_ID=?");
			ps.setInt(1, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while deleting cast-crew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}

	// Update cc
	public int updateCastCrew(CastCrew castCrew) throws DBException {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			if (castCrew.getProfileImgStream() != null) {
				ps = cn.prepareStatement("update tblcastcrew set name=?, profileImg=?, updatedTime=? where CC_ID=?");
				ps.setString(1, castCrew.getCastCrewName());
				ps.setBlob(2, castCrew.getProfileImgStream());
				ps.setTimestamp(3, castCrew.getUpdatedTime());
				ps.setInt(4, castCrew.getCastCrewId());
				// update with file
			} else {
				ps = cn.prepareStatement("update tblcastcrew set name=?, updatedTime=? where CC_ID=?");
				ps.setString(1, castCrew.getCastCrewName());
				ps.setTimestamp(2, castCrew.getUpdatedTime());
				ps.setInt(3, castCrew.getCastCrewId());
				// update without file
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while updating cast-crew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}

}
