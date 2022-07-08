package com.narola.bookmyseat.DTO;

import javax.servlet.http.Part;

public class MovieDTO {
	private String movieId;
	private String movieName;
	private String releaseDate;
	private String duration;
	private String censorRating;
	
	private String genre[];
	private String language[];
	
	private Part movieBanner;
	private Part moviePoster;
	
	private String type[];
	private String castCrewId[];
	private String Status;
	private String movieDescription;
	
	private String castCrewCreatedTime;
	private String genreCreatedTime;
	private String languageCreatedTime;
	
	public String getCastCrewCreatedTime() {
		return castCrewCreatedTime;
	}

	public void setCastCrewCreatedTime(String castCrewCreatedTime) {
		this.castCrewCreatedTime = castCrewCreatedTime;
	}

	public String getGenreCreatedTime() {
		return genreCreatedTime;
	}

	public void setGenreCreatedTime(String genreCreatedTime) {
		this.genreCreatedTime = genreCreatedTime;
	}

	public String getLanguageCreatedTime() {
		return languageCreatedTime;
	}

	public void setLanguageCreatedTime(String languageCreatedTime) {
		this.languageCreatedTime = languageCreatedTime;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCensorRating() {
		return censorRating;
	}

	public void setCensorRating(String censorRating) {
		this.censorRating = censorRating;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String[] getLanguage() {
		return language;
	}

	public void setLanguage(String[] language) {
		this.language = language;
	}

	public Part getMovieBanner() {
		return movieBanner;
	}

	public void setMovieBanner(Part movieBanner) {
		this.movieBanner = movieBanner;
	}

	public Part getMoviePoster() {
		return moviePoster;
	}

	public void setMoviePoster(Part moviePoster) {
		this.moviePoster = moviePoster;
	}

	public String[] getType() {
		return type;
	}

	public void setType(String[] type) {
		this.type = type;
	}

	public String[] getCastCrewId() {
		return castCrewId;
	}

	public void setCastCrewId(String[] castCrewId) {
		this.castCrewId = castCrewId;
	}

	public String getMovieDescription() {
		return movieDescription;
	}

	public void setMovieDescription(String movieDescription) {
		this.movieDescription = movieDescription;
	}
	
}
