package com.project.sellerapp.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utility {

	
	public static long getDays(Date date1, Date date2) {
		long diff = Math.abs( date2.getTime() - date1.getTime());
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;
	}
	
	public static double calculatePriceWithDiscount(double percent, double price) {
		return price*(100-percent)/100;
	}
	
	public static Date minusMonths(Date startDate, int months) {
		//Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(startDate); 
		c.add(Calendar.MONTH, -(months));
		System.out.println(c.getTime());
		return c.getTime();
	
	}
	
	public static Date getNextDay(Date fromDate) {
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(fromDate); 
		cal.add(Calendar.DATE, 1);
		Date nextDay = cal.getTime();
		return nextDay;
	}
	
	public static String dateToString(Date convertDate) {
		String pattern = "dd/MM/yyyy";

		DateFormat df = new SimpleDateFormat(pattern);

		String todayAsString = df.format(convertDate);

		return todayAsString;
	}
}
