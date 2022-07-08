package com.narola.bookmyseat.entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Table(name = "tblstates")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int stateId;

	@Column(name = "name", nullable = false)
	String stateName;

	@Column(name = "country_ID", nullable = true)
	int countryId;

	@Column(name = "createdTime")
	@Temporal(TemporalType.TIMESTAMP)
	Timestamp createdTime;

	@Column(name = "updatedTime")
	@Temporal(TemporalType.TIMESTAMP)
	Timestamp updatedTime;

	@OneToMany(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY,orphanRemoval = true)
	@JoinColumn(name = "stateId")
	private List<City> cityList;

	public List<City> getCityList() {
		return cityList;
	}

	public int getStateId() {
		return stateId;
	}
	public void setStateId(int state_id) {
		this.stateId = state_id;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String name) {
		this.stateName = name;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int country_id) {
		this.countryId = country_id;
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
