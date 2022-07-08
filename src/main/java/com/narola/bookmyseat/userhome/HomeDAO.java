package com.narola.bookmyseat.userhome;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.Genre;
import com.narola.bookmyseat.entity.Language;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.DBConnector;

public class HomeDAO {
	
	public static List<City> fetchCinemaCity(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection cn = DBConnector.getInstance().getConnection();
		List<City> al = new ArrayList<City>();
		try {
			ps = cn.prepareStatement("select * from tblcity where city_ID in (select DISTINCT city_ID from tbladdress where addressID in (select addressID from tblcinemas))");
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
	public static List<Movie> fetchRunningMovie(int city_ID){
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		Connection cn = DBConnector.getInstance().getConnection();
		List<Movie> al = new ArrayList<Movie>();
		try {
			ps = cn.prepareStatement("select * from tblmovie where status = 0 and movieID in (select movieID from tblshow where screenID in(select screenID from tblscreen where cinemasID in (select cinemasID from tblcinemas where status = 0 and addressID in (select addressID from tbladdress where city_ID = ?))))");
			ps.setInt(1, city_ID);
			rs = ps.executeQuery();
			while (rs.next()) {
				Movie movie = new Movie();
				movie.setMovieID(rs.getInt("movieID"));
				movie.setMovieTitle(rs.getString("title"));
				movie.setReleaseDate(rs.getDate("releaseDate"));
				movie.setDuration(rs.getString("duration"));
				movie.setCensorRating(rs.getString("censorRating"));
				movie.setDescription(rs.getString("description"));
				movie.setMoviePoster(rs.getBlob("moviePoster").getBinaryStream());
				movie.setMovieBanner(rs.getBlob("movieBanner").getBinaryStream());
				movie.setStatus(rs.getInt("status"));
				movie.setCreatedTime(rs.getTimestamp("createdTime"));
				movie.setUpdatedTime(rs.getTimestamp("updatedTime"));
				
				
				ps2 = cn.prepareStatement("select * from tblgenre where genreID in (select genreID from tblmoviegenre where movieID = ?)");
				ps2.setInt(1,rs.getInt("movieID"));
				rs2 = ps2.executeQuery();
				List<Genre> alGenre = new ArrayList<Genre>();
				while (rs2.next()) {
					Genre genre = new Genre();
					genre.setGenre(rs2.getString("genre"));
					alGenre.add(genre);
				}
				movie.setGenre(alGenre);
				
				ps3 = cn.prepareStatement("select * from tbllanguage where languageID in (select languageID from tblmovielanguage where movieID = ?)");
				ps3.setInt(1,rs.getInt("movieID"));
				rs3 = ps3.executeQuery();
				List<Language>  alLanguage= new ArrayList<Language>();
				while (rs3.next()) {
					Language language = new Language();
					language.setLanguage(rs3.getString("language"));
					alLanguage.add(language);
				}
				movie.setLanguage(alLanguage);
				al.add(movie);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetch running movie...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
}
