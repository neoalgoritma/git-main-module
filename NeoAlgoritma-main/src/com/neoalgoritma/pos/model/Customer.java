 package com.neoalgoritma.pos.model;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import com.neoalgoritma.model.Address;

public class Customer {
	
	public ObjectId id;
	public String company;
	public String firstname;
	public String lastname;
	public String title;
	public String email;
	public String mobile;
	public Address address = new Address();
	public String comment;
	public LocalDateTime lastUpdated;
	
	public Customer() {
	}

	
	public Customer(String company, String firstname, String lastname, String title, String email, String mobile,
			Address address, String comment, LocalDateTime lastUpdated) {
		super();
		this.company = company;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.comment = comment;
		this.lastUpdated = lastUpdated;
	}


	public Customer(ObjectId id, String company, String firstname, String lastname, String title, String email,
			String mobile, Address address, String comment, LocalDateTime lastUpdated) {
		super();
		this.id = id;
		this.company = company;
		this.firstname = firstname;
		this.lastname = lastname;
		this.title = title;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.comment = comment;
		this.lastUpdated = lastUpdated;
	}


	public ObjectId getId() {
		return id;
	}


	public void setId(ObjectId id) {
		this.id = id;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
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


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}


	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	
	


}
