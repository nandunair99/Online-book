package com.narola.bookmyseat.movies.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.narola.bookmyseat.entity.*;
import com.narola.bookmyseat.exception.DBException;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.DBConnector;
import org.springframework.stereotype.Repository;

public class MovieDAOPostgreas implements IMovieDAO {
	/***
	 * Get all records of Genre from database
	 * 
	 * @return Return List array of genre which hold all genre.
	 * 
	 * @throws DBException In case of Database related issues.
	 * 	
	 */
	public List<Genre> fetchAllGenre() throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Genre> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblgenre");
			rs = ps.executeQuery();
			while (rs.next()) {
				Genre genre = new Genre();
				genre.setGenreID(rs.getInt("genreID"));
				genre.setGenre(rs.getString("genre"));
				al.add(genre);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching genre...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	/***
	 * Get all records of Language from database
	 * 
	 * @return Return List array of language which hold all language.
	 * 
	 * @throws DBException In case of Database related issues.
	 * 	
	 */
	public List<Language> fetchAllLanguage() throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Language> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tbllanguage");
			rs = ps.executeQuery();
			while (rs.next()) {
				Language language = new Language();
				language.setLanguageID(rs.getInt("languageID"));
				language.setLanguage(rs.getString("language"));
				al.add(language);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching language...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	
	//insert movie/moviecastcrew/moviegenre/movielanguage
	
	public int insertMovie(Movie movie,MovieCastCrew[] moviecastcrew,MovieGenre[] moviegenre,MovieLanguage[] movielanguage) throws DBException{
		PreparedStatement ps = null;
		ResultSet res = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		Connection cn = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			
			cn.setAutoCommit(false);
			
			ps = cn.prepareStatement("insert into tblmovie (title,releaseDate,duration,censorRating,description,moviePoster,movieBanner,status,createdTime,updatedTime) values (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, movie.getMovieTitle());
			ps.setDate(2, movie.getReleaseDate());
			ps.setString(3, movie.getDuration());
			ps.setString(4, movie.getCensorRating());
			ps.setString(5, movie.getDescription());
			ps.setBlob(6, movie.getMoviePoster());
			ps.setBlob(7, movie.getMovieBanner());
			ps.setInt(8,Constant.STATUS_MOVIE_INACTIVE);
			ps.setTimestamp(9, movie.getCreatedTime());
			ps.setTimestamp(10, movie.getUpdatedTime());
			ps.executeUpdate();
			res = ps.getGeneratedKeys();
			res.next();
			int movieID = res.getInt(1);
	
			if(movieID!=0) {
				for(int i=0;i<moviecastcrew.length;i++)
				{
					ps2 = cn.prepareStatement("insert into tblmoviecastcrew (movieID,CC_ID,type,createdTime,updatedTime) values (?,?,?,?,?)");
					ps2.setInt(1, movieID);
					ps2.setInt(2, moviecastcrew[i].getCastCrewID());
					ps2.setInt(3, moviecastcrew[i].getCastCrewType());
					ps2.setTimestamp(4, moviecastcrew[i].getCreatedTime());
					ps2.setTimestamp(5, moviecastcrew[i].getUpdatedTime());
					ps2.executeUpdate();
				}
			}
		
			for(int i=0;i<moviegenre.length;i++)
			{
				ps3 = cn.prepareStatement("insert into tblmoviegenre (movieID,genreID,createdTime,updatedTime) values (?,?,?,?)");
				ps3.setInt(1, movieID);
				ps3.setInt(2, moviegenre[i].getGenreID());
				ps3.setTimestamp(3, moviegenre[i].getCreatedTime());
				ps3.setTimestamp(4, moviegenre[i].getUpdatedTime());
				ps3.executeUpdate();
			
			}
		
			for(int i=0;i<movielanguage.length;i++)
			{
				ps4 = cn.prepareStatement("insert into tblmovielanguage (movieID,languageID,createdTime,updatedTime) values (?,?,?,?)");
				ps4.setInt(1, movieID);
				ps4.setInt(2, movielanguage[i].getLanguageID());
				ps4.setTimestamp(3, movielanguage[i].getCreatedTime());
				ps4.setTimestamp(4, movielanguage[i].getUpdatedTime());
				ps4.executeUpdate();
			}
			cn.commit();
			return 1;
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			throw new DBException("Error while inserting movie...", e);
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
			DBConnector.getInstance().releaseResource(ps4);
			
		}
	}
	//movie update
	public int updateMovie(Movie movie,MovieCastCrew[] moviecastcrew,MovieGenre[] moviegenre,MovieLanguage[] movielanguage) throws DBException {
		PreparedStatement psdel1 = null;
		PreparedStatement psdel2 = null;
		PreparedStatement psdel3 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		Connection cn = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			cn.setAutoCommit(false);
			
			if (movie.getMovieBanner() != null && movie.getMoviePoster() == null) {
				ps = cn.prepareStatement("update tblmovie set title=?,releaseDate=?,duration=?,censorRating=?,description=?,movieBanner=?,status=?,updatedTime=? where movieID=?");
				ps.setString(1, movie.getMovieTitle());
				ps.setDate(2, movie.getReleaseDate());
				ps.setString(3, movie.getDuration());
				ps.setString(4, movie.getCensorRating());
				ps.setString(5, movie.getDescription());
				ps.setBlob(6, movie.getMovieBanner());
				ps.setInt(7,movie.getStatus());
				ps.setTimestamp(8, movie.getUpdatedTime());
				ps.setInt(9, movie.getMovieID());
			}else if(movie.getMovieBanner() == null && movie.getMoviePoster() != null){
				ps = cn.prepareStatement("update tblmovie set title=?,releaseDate=?,duration=?,censorRating=?,description=?,moviePoster=?,status=?,updatedTime=? where movieID=?");
				ps.setString(1, movie.getMovieTitle());
				ps.setDate(2, movie.getReleaseDate());
				ps.setString(3, movie.getDuration());
				ps.setString(4, movie.getCensorRating());
				ps.setString(5, movie.getDescription());
				ps.setBlob(6, movie.getMoviePoster());
				ps.setInt(7,movie.getStatus());
				ps.setTimestamp(8, movie.getUpdatedTime());
				ps.setInt(9, movie.getMovieID());
			}else if(movie.getMovieBanner() == null && movie.getMoviePoster() == null){
				ps = cn.prepareStatement("update tblmovie set title=?,releaseDate=?,duration=?,censorRating=?,description=?,status=?,updatedTime=? where movieID=?");
				ps.setString(1, movie.getMovieTitle());
				ps.setDate(2, movie.getReleaseDate());
				ps.setString(3, movie.getDuration());
				ps.setString(4, movie.getCensorRating());
				ps.setString(5, movie.getDescription());
				ps.setInt(6,movie.getStatus());
				ps.setTimestamp(7, movie.getUpdatedTime());
				ps.setInt(8, movie.getMovieID());
			}else if (movie.getMovieBanner() != null && movie.getMoviePoster() != null){
				ps = cn.prepareStatement("update tblmovie set title=?,releaseDate=?,duration=?,censorRating=?,description=?,movieBanner=?,moviePoster=?,status=?,updatedTime=? where movieID=?");
				ps.setString(1, movie.getMovieTitle());
				ps.setDate(2, movie.getReleaseDate());
				ps.setString(3, movie.getDuration());
				ps.setString(4, movie.getCensorRating());
				ps.setString(5, movie.getDescription());
				ps.setBlob(6, movie.getMovieBanner());
				ps.setBlob(7, movie.getMoviePoster());
				ps.setInt(8,movie.getStatus());
				ps.setTimestamp(9, movie.getUpdatedTime());
				ps.setInt(10, movie.getMovieID());
			}
			ps.executeUpdate();
			
			psdel1 = cn.prepareStatement("delete from tblmovielanguage where movieID=?");
			psdel1.setInt(1, movie.getMovieID());
			psdel1.executeUpdate();
			
			psdel2 = cn.prepareStatement("delete from tblmoviegenre where movieID=?");
			psdel2.setInt(1, movie.getMovieID());
			psdel2.executeUpdate();
			
			psdel3 = cn.prepareStatement("delete from tblmoviecastcrew where movieID=?");
			psdel3.setInt(1, movie.getMovieID());
			psdel3.executeUpdate();
			
			for(int i=0;i<moviecastcrew.length;i++)
			{
				ps2 = cn.prepareStatement("insert into tblmoviecastcrew (movieID,CC_ID,type,createdTime,updatedTime) values (?,?,?,?,?)");
				ps2.setInt(1, movie.getMovieID());
				ps2.setInt(2, moviecastcrew[i].getCastCrewID());
				ps2.setInt(3, moviecastcrew[i].getCastCrewType());
				ps2.setTimestamp(4, moviecastcrew[i].getCreatedTime());
				ps2.setTimestamp(5, moviecastcrew[i].getUpdatedTime());
				ps2.executeUpdate();
			}
			for(int i=0;i<moviegenre.length;i++)
			{
				ps3 = cn.prepareStatement("insert into tblmoviegenre (movieID,genreID,createdTime,updatedTime) values (?,?,?,?)");
				ps3.setInt(1, movie.getMovieID());
				ps3.setInt(2, moviegenre[i].getGenreID());
				ps3.setTimestamp(3, moviegenre[i].getCreatedTime());
				ps3.setTimestamp(4, moviegenre[i].getUpdatedTime());
				ps3.executeUpdate();
			
			}
		
			for(int i=0;i<movielanguage.length;i++)
			{
				ps4 = cn.prepareStatement("insert into tblmovielanguage (movieID,languageID,createdTime,updatedTime) values (?,?,?,?)");
				ps4.setInt(1, movie.getMovieID());
				ps4.setInt(2, movielanguage[i].getLanguageID());
				ps4.setTimestamp(3, movielanguage[i].getCreatedTime());
				ps4.setTimestamp(4, movielanguage[i].getUpdatedTime());
				ps4.executeUpdate();
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
			throw new DBException("Error while update movie...", e);
		} catch (DBException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		} finally {
			try {
				cn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			DBConnector.getInstance().releaseResource(ps);
			DBConnector.getInstance().releaseResource(ps2);
			DBConnector.getInstance().releaseResource(ps3);
			DBConnector.getInstance().releaseResource(ps4);
			DBConnector.getInstance().releaseResource(psdel1);
			DBConnector.getInstance().releaseResource(psdel2);
			DBConnector.getInstance().releaseResource(psdel3);
		}
	}
	//fetch all movie
	public List<Movie> fetchAllMovie() throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Movie> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblmovie");
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
				al.add(movie);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching movies...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	//fetch movie by id
	public Movie fetchMovieById(int movieId) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Movie movie = new Movie();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select * from tblmovie where movieID=?");
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
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
			}
			return movie;
		} catch (SQLException e) {
			throw new DBException("Error while fetching movie...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	//fetch CastCrew by movieId from moviecastcrew
	public List<CastCrew> fetchMovieCastCrewByMovieId(int movieId) throws DBException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CastCrew> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select c.CC_ID,c.name,c.profileImg,mc.type,mc.createdTime from tblcastcrew as c, tblmoviecastcrew as mc where c.CC_ID = mc.CC_ID and mc.movieID = ?");
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				byte[] img = rs.getBytes("profileImg");
				CastCrew castcrew = new CastCrew();
				castcrew.setCastCrewId(rs.getInt("CC_ID"));
				castcrew.setCastCrewName(rs.getString("name"));
				castcrew.setProfileImg(img);
				castcrew.setType(rs.getInt("type"));
				castcrew.setCreatedTime(rs.getTimestamp("createdTime"));
				al.add(castcrew);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching movie castcrew...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	//fetch Genre by movieId from movieGenre
	public List<Genre> fetchGenreByMovieId(int movieId) throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Genre> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select g.genre,mg.createdTime from tblgenre as g, tblmoviegenre as mg where (mg.movieID=?) and mg.genreID=g.genreID");
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Genre genre = new Genre();
				genre.setGenre(rs.getString("genre"));
				genre.setCreatedTime(rs.getTimestamp("createdTime"));
				al.add(genre);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching genre...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	//fetch Language by movieId from movieLanguage
	public List<Language> fetchLanguageByMovieId(int movieId) throws DBException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Language> al = new ArrayList<>();
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("select l.language,ml.createdTime from tbllanguage as l, tblmovielanguage as ml where l.languageID = ml.languageID and ml.movieID=?");
			ps.setInt(1, movieId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Language language = new Language();
				language.setLanguage(rs.getString("language"));
				language.setCreatedTime(rs.getTimestamp("createdTime"));
				al.add(language);
			}
			return al;
		} catch (SQLException e) {
			throw new DBException("Error while fetching language...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps, rs);
		}
	}
	//update Movie Status
	public int updateMovieStatus(int movieId,int status) throws DBException {
		PreparedStatement ps = null;
		try {
			Connection cn = DBConnector.getInstance().getConnection();
			ps = cn.prepareStatement("update tblmovie set status=?, updatedTime=? where movieID=?");
			ps.setInt(1, status);
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setInt(3,movieId);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new DBException("Error while updating movie status...", e);
		} catch (DBException e) {
			throw e;
		} finally {
			DBConnector.getInstance().releaseResource(ps);
		}
	}
	//delete movie
	public int deleteMovie(int movieId) throws DBException {
		Connection cn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		PreparedStatement ps4 = null;
		try {
			cn = DBConnector.getInstance().getConnection();
			cn.setAutoCommit(false);
			
			ps = cn.prepareStatement("delete from tblmovielanguage where movieID=?");
			ps.setInt(1, movieId);
			ps.executeUpdate();
			
			ps2 = cn.prepareStatement("delete from tblmoviegenre where movieID=?");
			ps2.setInt(1, movieId);
			ps2.executeUpdate();
			
			ps3 = cn.prepareStatement("delete from tblmoviecastcrew where movieID=?");
			ps3.setInt(1, movieId);
			ps3.executeUpdate();
			
			ps4 = cn.prepareStatement("delete from tblmovie where movieID=?");
			ps4.setInt(1, movieId);
			ps4.executeUpdate();
			
			cn.commit();
			return 1;
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DBException("Error while deleting movie...", e);
		} catch (DBException e) {
			try {
				if(cn!=null){
					cn.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		}finally {
			try {
				if(cn!=null){
					cn.setAutoCommit(true);
				}
			} catch (SQLException  e ) {
				e.printStackTrace();
			}
			DBConnector.getInstance().releaseResource(ps);
			DBConnector.getInstance().releaseResource(ps2);
			DBConnector.getInstance().releaseResource(ps3);
			DBConnector.getInstance().releaseResource(ps4);
		}
	}
}
