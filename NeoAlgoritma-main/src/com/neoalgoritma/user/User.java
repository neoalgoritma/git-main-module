package com.neoalgoritma.user;

import java.util.Date;

import org.bson.types.ObjectId;

public class User {

	public ObjectId id;
	public String firstname;
	public String lastname;
	public String password;
	public String email;
	public String mobile;
	public Date created;
	public boolean owner;
	public Address address;
	
	public User() {
		
	}

	public User(ObjectId id, String firstname, String lastname, String password, String email, String mobile,
			Date created, boolean owner, Address address) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.created = created;
		this.owner = owner;
		this.address = address;
	}

	public User(String firstname, String lastname, String password, String email, String mobile, boolean owner,
			Address address) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.owner = owner;
		this.address = address;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isOwner() {
		return owner;
	}

	public void setOwner(boolean owner) {
		this.owner = owner;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ ", email=" + email + ", mobile=" + mobile + ", created=" + created + ", owner=" + owner + ", address="
				+ address + "]";
	}

	

	
	
	
}
