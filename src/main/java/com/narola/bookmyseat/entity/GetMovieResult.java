package com.narola.bookmyseat.entity;

import java.util.List;

public class GetMovieResult {
	private Movie movieData;
	private String imgPoster;
	private String imgBanner;
	private List<CastCrew> castCrewData;
	private List<String> imgCastCrew;
	private List<Genre> genreData;
	private List<Language> languageData;

	public Movie getMovieData() {
		return movieData;
	}

	public void setMovieData(Movie movieData) {
		this.movieData = movieData;
	}

	public String getImgPoster() {
		return imgPoster;
	}

	public void setImgPoster(String imgPoster) {
		this.imgPoster = imgPoster;
	}

	public String getImgBanner() {
		return imgBanner;
	}

	public void setImgBanner(String imgBanner) {
		this.imgBanner = imgBanner;
	}

	public List<CastCrew> getCastCrewData() {
		return castCrewData;
	}

	public void setCastCrewData(List<CastCrew> castCrewData) {
		this.castCrewData = castCrewData;
	}

	public List<String> getImgCastCrew() {
		return imgCastCrew;
	}

	public void setImgCastCrew(List<String> imgCastCrew) {
		this.imgCastCrew = imgCastCrew;
	}

	public List<Genre> getGenreData() {
		return genreData;
	}

	public void setGenreData(List<Genre> genreData) {
		this.genreData = genreData;
	}

	public List<Language> getLanguageData() {
		return languageData;
	}

	public void setLanguageData(List<Language> languageData) {
		this.languageData = languageData;
	}
}
