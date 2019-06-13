package com.neoalgoritma.pos.dao;

import com.neoalgoritma.dao.GenericDAO;
import com.neoalgoritma.pos.model.Customer;

public class CustomerDAO extends GenericDAO<Customer> {
	
	public CustomerDAO(String databaseName, String collectionName) {
		super(databaseName, collectionName);
		// TODO Auto-generated constructor stub
	}

}
