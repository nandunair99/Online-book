package com.narola.bookmyseat.entity;

public class Screen {
	private int ScreenID;
	private String ScreenName;
	private int CinemaID;
	
	public int getScreenID() {
		return ScreenID;
	}
	public void setScreenID(int screenID) {
		ScreenID = screenID;
	}
	public String getScreenName() {
		return ScreenName;
	}
	public void setScreenName(String screenName) {
		ScreenName = screenName;
	}
	public int getCinemaID() {
		return CinemaID;
	}
	public void setCinemaID(int cinemaID) {
		CinemaID = cinemaID;
	}	
}
