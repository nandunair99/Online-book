package com.narola.bookmyseat.entity;

import java.util.List;

public class GetMoviesResult {
	private List<Genre> genreData;
	private List<Language> languageData;
	private List<CastCrew> castCrewData;
	private List<Movie> movieData;
	private List<String> imgPoster;

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

	public List<CastCrew> getCastCrewData() {
		return castCrewData;
	}

	public void setCastCrewData(List<CastCrew> castCrewData) {
		this.castCrewData = castCrewData;
	}

	public List<Movie> getMovieData() {
		return movieData;
	}

	public void setMovieData(List<Movie> movieData) {
		this.movieData = movieData;
	}

	public List<String> getImgPoster() {
		return imgPoster;
	}

	public void setImgPoster(List<String> imgPoster) {
		this.imgPoster = imgPoster;
	}
}
