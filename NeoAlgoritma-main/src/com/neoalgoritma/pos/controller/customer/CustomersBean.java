package com.neoalgoritma.pos.controller.customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import com.neoalgoritma.model.User;
import com.neoalgoritma.model.UserPosDatabase;
import com.neoalgoritma.pos.dao.CustomerDAO;
import com.neoalgoritma.pos.model.Customer;
import com.neoalgoritma.pos.util.Util;

public class CustomersBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public Customer selectedCustomer;
	private LazyDataModel<Customer> lazyModel;
	private List<Customer> filteredCustomers;
	 
	UserPosDatabase userPosDatabase;
	User loggedUser;
	Util posUtil = new Util();
	CustomerDAO customerDAO;
	
	@PostConstruct
	public void init() {
		loggedUser = posUtil.getLoggedUser();
    	userPosDatabase = posUtil.getUserPosDatabase(loggedUser.getId());
    	customerDAO = new CustomerDAO(userPosDatabase.getDatabaseName(), "customer");
    	lazyModel = new CustomerLazyDataModel(customerDAO);
		
	}

	 public void onRowSelect(SelectEvent event) {
	        System.out.println("Select event fired");
	       
	    }
	 
	public void editClicked() {
		
		try {
			if (selectedCustomer != null && selectedCustomer.getId()!= null)
			FacesContext.getCurrentInstance().getExternalContext().redirect("customer/edit-customer.xhtml?i=1&id="+selectedCustomer.getId());
			else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unable to Edit",  "Please select a customer") );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void viewClicked() {
		
		if (selectedCustomer != null  && selectedCustomer.getId()!=null) {
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('customerDetailDialogVar').show();");
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unable to View",  "Please select a customer") );
		}
	}

	public void deleteClicked() {
		if (selectedCustomer != null  && selectedCustomer.getId()!= null) {
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("PF('deleteConfirmDialogVar').show();");
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Unable to Delete",  "Please select a customer"));
	    }
		
	}
	
	public void deleteFromDB() {
		if(customerDAO.delete(selectedCustomer.getId().toString())) {
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("customers.xhtml?i=1");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	}

	public Customer getSelectedCustomer() {
		System.out.println("SelectedCustomer: " +selectedCustomer);
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		System.out.println("SelectedCustomer: " +selectedCustomer);
		
		this.selectedCustomer = selectedCustomer;
	}

	public LazyDataModel<Customer> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Customer> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<Customer> getFilteredCustomers() {
		return filteredCustomers;
	}

	public void setFilteredCustomers(List<Customer> filteredCustomers) {
		this.filteredCustomers = filteredCustomers;
	}
	

	

}
