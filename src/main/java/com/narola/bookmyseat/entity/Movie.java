package com.narola.bookmyseat.entity;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.List;

public class Movie {
    private int movieID;
    private String movieTitle;
    private java.sql.Date releaseDate;
    private String duration;
    private String censorRating;
    private String description;
    private InputStream moviePoster;
    private InputStream movieBanner;
    private int status;
    private List<Genre> genre;
    private List<Language> language;
	private String castCrewId[];
    private Timestamp createdTime;
    private Timestamp updatedTime;

	public String[] getCastCrewId() {
		return castCrewId;
	}
	public void setCastCrewId(String[] castCrewId) {
		this.castCrewId = castCrewId;
	}
	public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public java.sql.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(InputStream moviePoster) {
        this.moviePoster = moviePoster;
    }

    public InputStream getMovieBanner() {
        return movieBanner;
    }

    public void setMovieBanner(InputStream movieBanner) {
        this.movieBanner = movieBanner;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
