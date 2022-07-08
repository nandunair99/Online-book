package com.narola.bookmyseat.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static SimpleDateFormat format_yyyy_MM_dd_hh_mm_ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static SimpleDateFormat format_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	public static Date parseDate(SimpleDateFormat format, String date) throws ParseException {
		return format.parse(date);
	}

}
