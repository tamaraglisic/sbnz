package com.project.sellerapp.dto;

import javax.persistence.Column;

public class TicketUserDTO {
	
	private Long id;
	
	private String userType;
	private int count;
	
	public TicketUserDTO(Long id, String userType, int count) {
		super();
		this.id = id;
		this.userType = userType;
		this.count = count;
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

	
}
