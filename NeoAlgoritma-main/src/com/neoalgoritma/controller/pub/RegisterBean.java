package com.neoalgoritma.controller.pub;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.neoalgoritma.user.UserDAO;

public class RegisterBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	com.neoalgoritma.util.Utils utils;
	String firstname;
	String lastname;
	String email;
	String password;
	String passwordRe;
	String company;
	String address;
	String postcode;
	String country;
	String mobile;
	UserDAO user = new UserDAO("neoalgoritma","user");
	
	@PostConstruct
	public void init() {
	}
	
	public void register() {
		System.out.println("registering....");
		boolean formValid = true;
		
		if(!utils.isValidEmailAddress(email)) {
			FacesContext.getCurrentInstance().addMessage("createAccountForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Invalid email address", "Invalid email address"));
			FacesContext.getCurrentInstance().addMessage("createAccountForm:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Invalid password", "Invalid password"));
			formValid = false;
		}
		if(!password.contentEquals(passwordRe)) {
			FacesContext.getCurrentInstance().addMessage("createAccountForm:password",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Password entered is different", "Password entered is different"));
			FacesContext.getCurrentInstance().addMessage("createAccountForm:passwordRe",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Password entered is different", "Re-enter exact same password"));
			formValid = false;
		}
		if(formValid) {
			user.toString();
			if (!user.emailRegistered(this.email)){
				FacesContext.getCurrentInstance().addMessage("createAccountForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Email already registered on system", "Email already registered on system"));
				formValid = false;
			}
		}
		
		if(formValid) {
			System.out.println("form valid");
			//user = new UserDAO(firstname,lastname,password,email,address,mobile,new Date());
			/*
			int userId = 0;
				try {
					userId = user.insertToDB();
					PrimeFaces current = PrimeFaces.current();
					current.executeScript("redirectToMain();");
				
				} catch (Exception e) {
					System.out.println("Error register....");
					e.printStackTrace();
				}
			*/
		}
		
		System.out.println(formValid);
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
		this.password = password;
	}

	public String getPasswordRe() {
		return passwordRe;
	}

	public void setPasswordRe(String passwordRe) {
		this.passwordRe = passwordRe;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
