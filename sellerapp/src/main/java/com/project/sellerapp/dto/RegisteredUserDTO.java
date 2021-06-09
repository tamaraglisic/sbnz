package com.project.sellerapp.dto;

import java.util.Set;

import com.project.sellerapp.model.Tickets;

public class RegisteredUserDTO {
	private Set<TicketsDTO> tickets;
	

	public RegisteredUserDTO(Set<TicketsDTO> tickets) {
		super();
		this.tickets = tickets;
	}

	public RegisteredUserDTO() {
		super();
	}

	public Set<TicketsDTO> getTickets() {
		return tickets;
	}

	public void setTickets(Set<TicketsDTO> tickets) {
		this.tickets = tickets;
	}
	
}
