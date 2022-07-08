package com.narola.bookmyseat.entity;

import java.util.List;

public class GetPlaces {
	private List<State> stateData;
	private List<City> cityData;

	public List<State> getStateData() {
		return stateData;
	}

	public void setStateData(List<State> stateData) {
		this.stateData = stateData;
	}

	public List<City> getCityData() {
		return cityData;
	}

	public void setCityData(List<City> cityData) {
		this.cityData = cityData;
	}

}
