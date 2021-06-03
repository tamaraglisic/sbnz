package com.project.sellerapp.dto;

import java.util.Set;

import com.project.sellerapp.model.Tickets;

public class RegisteredUserDTO {
	private Set<Tickets> tickets;
	

	public RegisteredUserDTO(Set<Tickets> tickets) {
		super();
		this.tickets = tickets;
	}

	public RegisteredUserDTO() {
		super();
	}

	public Set<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Tickets> tickets) {
		this.tickets = tickets;
	}
	
}
