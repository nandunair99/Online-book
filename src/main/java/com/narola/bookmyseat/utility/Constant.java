package com.narola.bookmyseat.utility;

public class Constant {
	
	public static final String URL_CITY_INSERT = "/location/insertcity";
	public static final String URL_CITY_UPDATE = "/location/updatecity";
	public static final String URL_STATE_INSERT = "/location/insertstate";
	public static final String URL_STATE_UPDATE = "/location/updatestate";
	public static final String URL_CASTCREW_INSERT = "/InsertCastCrewServlet";
	public static final String URL_CASTCREW_UPDATE = "/UpdateCastCrewServlet";
	public static final String URL_MOVIE_INSERT = "/movies/insertmovie";
	public static final String URL_MOVIE_UPDATE = "/movies/updatemovie";
	public static final String URL_VIEW_MOVIE = "/ViewMovie";
	public static final String URL_MOVIE_UPDATEDATA = "/MovieUpdateData";
	public static final String TYPE_CAST = "0";
	public static final String TYPE_CREW = "1";
	public static final String ERROR_OCCURS = "error";
	public static final String ERROR_OCCURS_YES = "yes";
	public static final String ERROR_OCCURS_NO = "no";
	public static final String ERROR_OCCURS_OPSMSG = "Oops, Something went wrong..!!!";
	public static final String ERROR_TEXTMSG = "errortext";
	public static final String SUCCESS_TEXT_INSERTMSG = "successfully Inserted...";
	public static final String SUCCESS_TEXT_UPDATEMSG = "successfully Updated...";
	public static final String ERROR_TEXT_OPRATIONFAIL = "Opration fail...";
	public static final String SUCCESS_TEXT_DELETEMSG = "delete successfully...";
	public static final int STATUS_MOVIE_ACTIVE = 0;
	public static final int STATUS_MOVIE_INACTIVE = 1;
	public static final int STATUS_MOVIE_UPCOMING = 2;
	public static final String URL_CINEMA_INSERT = "/InsertCinemaServlet";
	public static final String URL_CINEMA_UPDATE = "/UpdateCinemaServlet";
	public static final String URL_VIEW_CINEMA = "/ViewCinema";
	public static final String URL_CINEMA_UPDATEDATA = "/UpdateCinemaData";
	public static final String URL_SHOW_INSERT = "/InsertShow";
	public static final String URL_SHOW_UPDATE = "/UpdateShow";
	
	public static final int USER_TYPE_SUPERADMIN = 0;
	public static final int USER_TYPE_ADMIN = 1;
	public static final int USER_TYPE_SUPERCINEMAADMIN = 2;
	public static final int USER_TYPE_CINEMAADMIN = 3;
	public static final int USER_TYPE_ENDUSER = 4;
}
