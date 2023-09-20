package com.calculator.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String dateToString(Date date) {
	
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		String result = simpleDateFormat.format(date);
		return result;
		
	}
	
}
