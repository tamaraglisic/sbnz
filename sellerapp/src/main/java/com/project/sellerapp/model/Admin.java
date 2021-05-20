package com.project.sellerapp.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin  extends User{

	public Admin() {
		super();
	}

	public Admin(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified, Set<Authority> authority) {
		super(id, firstName, lastName, email, password, active, verified, authority);
	}

	public Admin(String email2, String password2, String firstName2, String lastName2) {
		super(email2, password2, firstName2, lastName2);
	}

	public Admin(String email, String password) {
		super(email, password);
	}

	public Admin(String firstName, String lastName, String email, String password, Boolean active, Boolean verified,
			Set<Authority> authority) {
		super(firstName, lastName, email, password, active, verified, authority);
	}

	
}
