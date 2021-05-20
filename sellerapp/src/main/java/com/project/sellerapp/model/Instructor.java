package com.project.sellerapp.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("instructor")
public class Instructor extends User{
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<SkiResort> skiResort;

	public Instructor() {
		super();
	}

	public Instructor(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified, Set<Authority> authority) {
		super(id, firstName, lastName, email, password, active, verified, authority);
	}

	public Instructor(String email2, String password2, String firstName2, String lastName2) {
		super(email2, password2, firstName2, lastName2);
	}

	public Instructor(String email, String password) {
		super(email, password);
	}

	public Instructor(Set<SkiResort> skiResort) {
		super();
		this.skiResort = skiResort;
	}

	public Set<SkiResort> getSkiResort() {
		return skiResort;
	}

	public void setSkiResort(Set<SkiResort> skiResort) {
		this.skiResort = skiResort;
	}
	
	
	

}
