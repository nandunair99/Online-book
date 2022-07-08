package com.narola.bookmyseat.entity;

public class ScreenSeatType {
	private int ScreenSeatTypeID;
	private String SeatType;
	private int ScreenID;
	private int NoOfRow;
	
	public int getScreenSeatTypeID() {
		return ScreenSeatTypeID;
	}
	public void setScreenSeatTypeID(int screenSeatTypeID) {
		ScreenSeatTypeID = screenSeatTypeID;
	}
	public String getSeatType() {
		return SeatType;
	}
	public void setSeatType(String seatType) {
		SeatType = seatType;
	}
	public int getScreenID() {
		return ScreenID;
	}
	public void setScreenID(int screenID) {
		ScreenID = screenID;
	}
	public int getNoOfRow() {
		return NoOfRow;
	}
	public void setNoOfRow(int noOfRow) {
		NoOfRow = noOfRow;
	}
	
}
