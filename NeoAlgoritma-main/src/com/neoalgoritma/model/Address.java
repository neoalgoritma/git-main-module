package com.neoalgoritma.model;

public class Address {

	public String street;
	public String state;
	public String city;
	public String country;
	public String postcode;
	
	public Address() {
		
	}
	public Address(String street, String state, String city, String country, String postcode) {
	
		this.street = street;
		this.state = state;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", state=" + state + ", city=" + city + ", country=" + country
				+ ", postcode=" + postcode + "]";
	}
	
	
	

	
	
	
}
