package com.narola.bookmyseat.DTO;

public class ScreenSeatTypeDTO {
	 private String ScreenSeatTypeID;
	 private String SeatType[];
	 private String ScreenID;
	 private String NoOfRow[];
	public String getScreenSeatTypeID() {
		return ScreenSeatTypeID;
	}
	public void setScreenSeatTypeID(String screenSeatTypeID) {
		ScreenSeatTypeID = screenSeatTypeID;
	}
	public String[] getSeatType() {
		return SeatType;
	}
	public void setSeatType(String[] seatType) {
		SeatType = seatType;
	}
	public String getScreenID() {
		return ScreenID;
	}
	public void setScreenID(String screenID) {
		ScreenID = screenID;
	}
	public String[] getNoOfRow() {
		return NoOfRow;
	}
	public void setNoOfRow(String[] noOfRow) {
		NoOfRow = noOfRow;
	} 
}
