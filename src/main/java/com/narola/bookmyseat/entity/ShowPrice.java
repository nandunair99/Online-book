package com.narola.bookmyseat.entity;

public class ShowPrice {
	private int showPriceID;
	private int showID;
	private int seatTypeID;
	private double price;
	public int getShowPriceID() {
		return showPriceID;
	}
	public void setShowPriceID(int showPriceID) {
		this.showPriceID = showPriceID;
	}
	public int getShowID() {
		return showID;
	}
	public void setShowID(int showID) {
		this.showID = showID;
	}
	public int getSeatTypeID() {
		return seatTypeID;
	}
	public void setSeatTypeID(int seatTypeID) {
		this.seatTypeID = seatTypeID;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
