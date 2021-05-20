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
	
	@Column(unique = false, nullable = false)
	private int count;

	public TicketUser(TicketUserDTO t) {
		this.id = t.getId();
		this.userType = t.getUserType();
		this.count = t.getCount();
	}
	
	public TicketUser() {
		super();
	}

	public TicketUser(Long id, String userType, int count) {
		super();
		this.id = id;
		this.userType = userType;
		this.count = count;
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
	
	
}
