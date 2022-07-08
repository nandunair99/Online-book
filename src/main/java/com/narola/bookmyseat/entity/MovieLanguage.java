package com.narola.bookmyseat.entity;

import java.sql.Timestamp;

public class MovieLanguage {
	private int movieLanguageID;
	private int movieID;
	private int languageID;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	
	public int getMovieLanguageID() {
		return movieLanguageID;
	}
	public void setMovieLanguageID(int movieLanguageID) {
		this.movieLanguageID = movieLanguageID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getLanguageID() {
		return languageID;
	}
	public void setLanguageID(int languageID) {
		this.languageID = languageID;
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
