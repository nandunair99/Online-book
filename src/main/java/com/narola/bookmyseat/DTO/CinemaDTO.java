package com.narola.bookmyseat.DTO;

public class CinemaDTO {
	private String cinemaId;
	private String addressId;
	private String cinemaName;
	private String cinemaFacilities;
	private String cinemaStatus;
	public String getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(String cinemaId) {
		this.cinemaId = cinemaId;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getCinemaFacilities() {
		return cinemaFacilities;
	}
	public void setCinemaFacilities(String cinemaFacilities) {
		this.cinemaFacilities = cinemaFacilities;
	}
	public String getCinemaStatus() {
		return cinemaStatus;
	}
	public void setCinemaStatus(String cinemaStatus) {
		this.cinemaStatus = cinemaStatus;
	}
}
