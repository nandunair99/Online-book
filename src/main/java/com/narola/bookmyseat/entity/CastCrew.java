package com.narola.bookmyseat.entity;

import java.io.InputStream;
import java.sql.Timestamp;

public class CastCrew {
	private int castCrewId;
	private String castCrewName;
	private InputStream profileImgStream;
	private byte[] profileImg;
	private int type;
	private Timestamp createdTime;
	private Timestamp updatedTime;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public InputStream getProfileImgStream() {
		return profileImgStream;
	}

	public void setProfileImgStream(InputStream profileImgStream) {
		this.profileImgStream = profileImgStream;
	}

	public int getCastCrewId() {
		return castCrewId;
	}

	public void setCastCrewId(int cc_id) {
		this.castCrewId = cc_id;
	}

	public String getCastCrewName() {
		return castCrewName;
	}

	public void setCastCrewName(String name) {
		this.castCrewName = name;
	}

	public byte[] getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(byte[] profileImg) {
		this.profileImg = profileImg;
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
