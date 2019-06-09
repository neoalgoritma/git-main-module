package com.neoalgoritma.user;

public class Address {

	public String address;
	public String country;
	public String postcode;
	
	public Address() {
		
	}

	
	public Address(String address, String country, String postcode) {
		this.address = address;
		this.country = country;
		this.postcode = postcode;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "Address [address=" + address + ", country=" + country + ", postcode=" + postcode + "]";
	}
	
	
	
	
}
