package com.project.sellerapp.dto;

import javax.persistence.Column;

public class TicketUserDTO {
	
	private Long id;
	
	private String userType;
	private int count;
	private double singleTicketPrice;
	
	public TicketUserDTO(Long id, String userType, int count, double singleTicketPrice) {
		super();
		this.id = id;
		this.userType = userType;
		this.count = count;
		this.singleTicketPrice = singleTicketPrice;
	}

	public TicketUserDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getSingleTicketPrice() {
		return singleTicketPrice;
	}

	public void setSingleTicketPrice(double singleTicketPrice) {
		this.singleTicketPrice = singleTicketPrice;
	}

	public void addDiscountOnTicketPrice(double percent) {
		this.singleTicketPrice = this.singleTicketPrice*(100-percent)/100;
	}
	
	public void increaseTicketPrice(double percent) {
		this.singleTicketPrice = this.singleTicketPrice*(100+percent)/100;
	}
	
}
