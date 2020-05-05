package com.uab.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.uab.project.config.exception.ResourceException;
import com.uab.project.config.exception.ResourceException.ResponseCode;

public class DateUtils {

	public static Date dateConvert(String date, String format){
		Date returnDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			returnDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ResourceException(ResponseCode.INTERNAL_SERVER_ERROR, "No es posible convertir esta fecha");
		}
		return returnDate;
	}

	public static String dateConvert(Date date, String format) {
		Locale locale = new Locale("es", "ES");
		SimpleDateFormat f1 = new SimpleDateFormat(format, locale);
		String strDate = f1.format(date);
		return strDate;
	}
}
