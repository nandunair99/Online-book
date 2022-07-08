package com.narola.bookmyseat.entity;

import java.sql.Date;
import java.util.List;

public class Show {
	private int showID;
	private int movieID;
	private Date showDate;
	private String showTime;
	private String endTime;
	private int screenID;
	private List<ShowPrice> showPrice;
	
	public List<ShowPrice> getShowPrice() {
		return showPrice;
	}
	public void setShowPrice(List<ShowPrice> showPrice) {
		this.showPrice = showPrice;
	}
	public int getShowID() {
		return showID;
	}
	public void setShowID(int showID) {
		this.showID = showID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getScreenID() {
		return screenID;
	}
	public void setScreenID(int screenID) {
		this.screenID = screenID;
	}
	
}
