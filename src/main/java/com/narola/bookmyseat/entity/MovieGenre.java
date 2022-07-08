package com.narola.bookmyseat.entity;

import java.sql.Timestamp;

public class MovieGenre {
	private int movieGenreID;
	private int movieID;
	private int genreID;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	
	public int getMovieGenreID() {
		return movieGenreID;
	}
	public void setMovieGenreID(int movieGenreID) {
		this.movieGenreID = movieGenreID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getGenreID() {
		return genreID;
	}
	public void setGenreID(int genreID) {
		this.genreID = genreID;
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
