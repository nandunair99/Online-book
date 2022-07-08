package com.narola.bookmyseat.entity;

import java.sql.Timestamp;

public class Cinemas {
	private int cinemasID;
	private String name;
	private int addressID;
	private String facilities;
	private int status;
	private Address addressData;
	
	public Address getAddressData() {
		return addressData;
	}
	public void setAddressData(Address addressData) {
		this.addressData = addressData;
	}
	private Timestamp createdTime;
	private Timestamp updatedTime;
	public int getCinemasID() {
		return cinemasID;
	}
	public void setCinemasID(int cinemasID) {
		this.cinemasID = cinemasID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getFacilities() {
		return facilities;
	}
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
