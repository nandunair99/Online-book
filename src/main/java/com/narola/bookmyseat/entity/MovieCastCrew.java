package com.narola.bookmyseat.entity;

import java.sql.Timestamp;

public class MovieCastCrew {
	private int movieCastCrewID;
	private int movieID;
	private int CastCrewID;
	private int CastCrewType;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	
	public int getMovieCastCrewID() {
		return movieCastCrewID;
	}
	public void setMovieCastCrewID(int movieCastCrewID) {
		this.movieCastCrewID = movieCastCrewID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getCastCrewID() {
		return CastCrewID;
	}
	public void setCastCrewID(int castCrewID) {
		CastCrewID = castCrewID;
	}
	public int getCastCrewType() {
		return CastCrewType;
	}
	public void setCastCrewType(int castCrewType) {
		CastCrewType = castCrewType;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
}
