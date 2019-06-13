package com.neoalgoritma.pos.controller.customer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import com.neoalgoritma.model.Address;
import com.neoalgoritma.model.User;
import com.neoalgoritma.model.UserPosDatabase;
import com.neoalgoritma.pos.dao.CustomerDAO;
import com.neoalgoritma.pos.model.Customer;
import com.neoalgoritma.pos.util.Util;

public class PosNewCustomerBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	//CustomerDao customerDAO = new CustomerDao(databaseName, collectionName)
	UserPosDatabase userPosDatabase;
	User loggedUser;
	Util posUtil = new Util();
	public Customer customer = new Customer();
	CustomerDAO customerDAO;
	//public Address address = new Address();
	public String test;
	
	@PostConstruct
    public void init() {
		
		loggedUser = posUtil.getLoggedUser();
    	userPosDatabase = posUtil.getUserPosDatabase(loggedUser.getId());
    	customerDAO = new CustomerDAO(userPosDatabase.getDatabaseName(), "customer");
    	
    	//address = new Address("street","state","city","country","postcode");
    	//customer = new Customer("testC", "firstname", "lastname", "title", "email", "mobile", address, "comment", LocalDateTime.now());
    	//customerDAO.insert(customer);
    	//System.out.println(userPosDatabase);
    }

	public void save() {
		if(!posUtil.isValidEmailAddress(customer.getEmail())) {
			FacesContext.getCurrentInstance().addMessage("posNewCustomerForm:email", 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid email address",  "Invalid email address") );
			
		}
		else {
			customerDAO.insert(customer);
			PrimeFaces.current().executeScript("redirectTo('Customer','Detail saved','success','customers.xhtml');");
		}
			
		System.out.println("Saving customer: " + customer);
	}

	
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public UserPosDatabase getUserPosDatabase() {
		return userPosDatabase;
	}


	public void setUserPosDatabase(UserPosDatabase userPosDatabase) {
		this.userPosDatabase = userPosDatabase;
	}


	public User getLoggedUser() {
		return loggedUser;
	}


	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}



	
	
}
