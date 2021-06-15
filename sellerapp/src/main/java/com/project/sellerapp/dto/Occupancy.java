package com.project.sellerapp.dto;

import java.util.Date;

public class Occupancy {
	
	private String forDay;
	private double percent;
	
	public Occupancy() {
		super();
	}

	
	public Occupancy(String forDay, double percent) {
		super();
		this.forDay = forDay;
		this.percent = percent;
	}


	public String getForDay() {
		return forDay;
	}


	public void setForDay(String forDay) {
		this.forDay = forDay;
	}


	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	

}
