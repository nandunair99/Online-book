package com.narola.bookmyseat.entity;

import java.io.InputStream;

public class User {
	private int userID;
	private String firstName;
	private String lastName;
	private String phoneno;
	private int addressID;
	private int userTyp;
	private InputStream profileImg;	
	private String profileImgAsBase64;
	private String emailID;
	private String password;
	private Address addressData;
	private java.sql.Timestamp createdTime;
	private java.sql.Timestamp updatedTime;	
	
	public java.sql.Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(java.sql.Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public java.sql.Timestamp getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(java.sql.Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}
	public Address getAddressData() {
		return addressData;
	}
	public void setAddressData(Address addressData) {
		this.addressData = addressData;
	}
	public int getUserID() {
		return userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public int getUserTyp() {
		return userTyp;
	}
	public void setUserTyp(int userTyp) {
		this.userTyp = userTyp;
	}
	public InputStream getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(InputStream profileImg) {
		this.profileImg = profileImg;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImgAsBase64() {
		return profileImgAsBase64;
	}
	public void setProfileImgAsBase64(String profileImgAsBase64) {
		this.profileImgAsBase64 = profileImgAsBase64;
	}
	
}
