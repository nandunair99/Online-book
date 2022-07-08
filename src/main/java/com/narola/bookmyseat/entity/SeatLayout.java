package com.narola.bookmyseat.entity;

public class SeatLayout {
	private int screenSeatID;
	private String seatNo;
	private int seatTypeID;
	private int status;
	public int getScreenSeatID() {
		return screenSeatID;
	}
	public void setScreenSeatID(int screenSeatID) {
		this.screenSeatID = screenSeatID;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	public int getSeatTypeID() {
		return seatTypeID;
	}
	public void setSeatTypeID(int seatTypeID) {
		this.seatTypeID = seatTypeID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
