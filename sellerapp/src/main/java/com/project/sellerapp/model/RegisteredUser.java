package com.project.sellerapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("registered_user")
public class RegisteredUser  extends User{

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<Tickets> tickets;

	public RegisteredUser() {
		super();
		
	}

	public RegisteredUser(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified, Set<Authority> authority) {
		super(id, firstName, lastName, email, password, active, verified, authority);
		
	}

	public RegisteredUser(String email2, String password2, String firstName2, String lastName2) {
		super(email2, password2, firstName2, lastName2);
		
	}

	public RegisteredUser(String email, String password) {
		super(email, password);
	
	}

	public RegisteredUser(Set<Tickets> tickets) {
		super();
		this.tickets = tickets;
	}

	public Set<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(Set<Tickets> tickets) {
		this.tickets = tickets;
	}
	
	
}
