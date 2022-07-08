package com.narola.bookmyseat.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "tblcity")
public class City {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cityId;
	@Column
	private String cityName;

	private int stateId;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp updatedTime;

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String name) {
		this.cityName = name;
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
