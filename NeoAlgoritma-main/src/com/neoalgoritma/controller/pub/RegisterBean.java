package com.neoalgoritma.controller.pub;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.PrimeFaces;

import com.neoalgoritma.dao.UserDAO;
import com.neoalgoritma.model.Address;
import com.neoalgoritma.model.User;
import com.neoalgoritma.util.Utils;

public class RegisterBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	Utils utils = new Utils();
	String firstname;
	String lastname;
	String email;
	String password;
	String passwordRe;
	String company;
	String street;
	String state;
	String city;
	String postcode;
	String country;
	String mobile;
	UserDAO userDAO = new UserDAO("neoalgoritma","user");
	
	@PostConstruct
	public void init() {
	}
	
	public void register() {
		System.out.println("registering....");
		boolean formValid = true;
		
		if(!utils.isValidEmailAddress(email)) {
			System.out.println("email not same");
			FacesContext.getCurrentInstance().addMessage("registerForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Invalid email address", "Invalid email address"));
			formValid = false;
		}
		if(!password.contentEquals(passwordRe)) {
			FacesContext.getCurrentInstance().addMessage("registerForm:password",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Password entered is different", "Password entered is different"));
			FacesContext.getCurrentInstance().addMessage("registerForm:passwordRe",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Password entered is different", "Re-enter exact same password"));
			formValid = false;
		}
		if(formValid) {
			
			if (userDAO.emailRegistered(this.email)){
				FacesContext.getCurrentInstance().addMessage("registerForm:email", new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Email already registered on system", "Email already registered on system"));
				formValid = false;
			}
		}
		
		if(formValid) {
			System.out.println("form valid");
			Address address = new Address(street, state, city, country, postcode);
			User user = new User(firstname, lastname, password, email, mobile, false, address);
			
			try {
				if(userDAO.insert(user) != null) {
					HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
					session.setAttribute("loggedUser", user);
					PrimeFaces current = PrimeFaces.current();
					current.executeScript("redirectToMainRegister();");
				}
				
			} catch (Exception e) {
				System.out.println("Error register....");
				e.printStackTrace();
			}
			
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
