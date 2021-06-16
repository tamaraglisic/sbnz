package com.project.sellerapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.sellerapp.dto.TicketUserDTO;

@Entity
public class TicketUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = false, nullable = false)
	private String userType;
	
	@Column(unique = false, nullable = true)
	private int counts;
	
	@Column(unique = false, nullable = true)
	private double singleTicketPrice;

	public TicketUser(TicketUserDTO t) {
		this.id = t.getId();
		this.userType = t.getUserType();
		this.counts = t.getCount();
		this.singleTicketPrice = t.getSingleTicketPrice();
	}
	
	public TicketUser() {
		super();
	}
	

	public TicketUser(String userType, int counts, double singleTicketPrice) {
		super();
		this.userType = userType;
		this.counts = counts;
		this.singleTicketPrice = singleTicketPrice;
	}

	public TicketUser(Long id, String userType, int count, double singleTicketPrice) {
		super();
		this.id = id;
		this.userType = userType;
		this.counts = count;
		this.singleTicketPrice = singleTicketPrice;
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
		return counts;
	}

	public void setCount(int count) {
		this.counts = count;
	}

	public double getSingleTicketPrice() {
		return singleTicketPrice;
	}

	public void setSingleTicketPrice(double singleTicketPrice) {
		this.singleTicketPrice = singleTicketPrice;
	}
	
	
}
