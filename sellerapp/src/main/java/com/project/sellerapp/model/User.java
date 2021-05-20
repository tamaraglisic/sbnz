package com.project.sellerapp.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.sellerapp.model.Authority;



import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(nullable = true)
	protected String firstName;

	@Column(nullable = true)
	protected String lastName;

	@Column(unique = true)
	protected String email;

	@Column(unique=false,nullable = false)
	protected String password;

	@Column(nullable = true)
	protected Boolean active;

	@Column(nullable = true)
	protected Boolean verified;


	@ManyToMany(fetch = FetchType.EAGER)
	//@JoinColumn(name = "user_id", nullable = false)
	protected Set<Authority> authority;

	@Column(name = "last_password_reset_date",nullable = true)
    private Timestamp lastPasswordResetDate;
	
	public User() {
		super();
	}
	
	public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

	public User(Long id, String firstName, String lastName, String email, String password, Boolean active,
			Boolean verified, Set<Authority> authority) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.verified = verified;
		this.authority = authority;
	}

	public User(String firstName, String lastName, String email, String password, Boolean active, Boolean verified,
			Set<Authority> authority) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.verified = verified;
		this.authority = authority;
	}

	public User(String email2, String password2, String firstName2, String lastName2) {
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.email = email2;
		this.password = password2;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getVerified() {
		return verified;
	}

	public void setVerified(Boolean verified) {
		this.verified = verified;
	}


	public Set<Authority> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<Authority> set) {
		this.authority = set;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
    public boolean isEnabled() {
        return true;
    }

    public Timestamp getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authority;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", active=" + active + ", verified=" + verified
				+ ", authority=" + authority + ", lastPasswordResetDate=" + lastPasswordResetDate + "]";
	}

	

}