package com.narola.bookmyseat.DTO;

public class ShowDTO {
	private String showID;
	private String movieID;
	private String showDate;
	private String showTime;
	private String endTime;
	private String screenID;
	private String[] SeatTypeID;
	private String[] SeatTypePrice;
	public String getShowID() {
		return showID;
	}
	public void setShowID(String showID) {
		this.showID = showID;
	}
	public String getMovieID() {
		return movieID;
	}
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	public String getShowDate() {
		return showDate;
	}
	public void setShowDate(String showDate) {
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
	public String getScreenID() {
		return screenID;
	}
	public void setScreenID(String screenID) {
		this.screenID = screenID;
	}
	public String[] getSeatTypeID() {
		return SeatTypeID;
	}
	public void setSeatTypeID(String[] seatTypeID) {
		SeatTypeID = seatTypeID;
	}
	public String[] getSeatTypePrice() {
		return SeatTypePrice;
	}
	public void setSeatTypePrice(String[] seatTypePrice) {
		SeatTypePrice = seatTypePrice;
	}
}
