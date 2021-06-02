package com.project.sellerapp.helpers;

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
}
