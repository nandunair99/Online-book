package com.narola.bookmyseat.DTO;

import java.io.InputStream;

import javax.servlet.http.Part;

public class CastCrewDTO {
	private String castCrewId;
	private String castCrewName;
	private Part castCrewProfile;
	private InputStream imgStream;
	
	public InputStream getImgStream() {
		return imgStream;
	}

	public void setImgStream(InputStream imgStream) {
		this.imgStream = imgStream;
	}

	public String getCastCrewId() {
		return castCrewId;
	}

	public void setCastCrewId(String castCrewId) {
		this.castCrewId = castCrewId;
	}

	public String getCastCrewName() {
		return castCrewName;
	}

	public void setCastCrewName(String castCrewName) {
		this.castCrewName = castCrewName;
	}

	public Part getCastCrewProfile() {
		return castCrewProfile;
	}

	public void setCastCrewProfile(Part castCrewProfile) {
		this.castCrewProfile = castCrewProfile;
	}

}
