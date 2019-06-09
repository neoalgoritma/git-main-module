package com.neoalgoritma.user;

import java.util.Arrays;

import org.bson.Document;

public class UserDAO extends GenericDAO<User> {

	public UserDAO(String databaseName,String collectionName) {
		super(databaseName,collectionName);
		// TODO Auto-generated constructor stub
	}

	public User getUserLogin(String email,String password) {
		User loggedUser = new User();
		Document query = new Document();
		query.append("$and", Arrays.asList(
                new Document()
                        .append("email", email),
                new Document()
                        .append("password", password)
            )
        );
       
		loggedUser = findDocument(query);
        
		return loggedUser;
	}

	
	public boolean emailRegistered(String email) {
		
		boolean registered = false;
		Document query = new Document();
		query.append("email", email);
		System.out.println("emailRegistered:" + query);
		if(findDocument(query) != null) { 
			registered = true;
		}
			
		return registered;
	}

	
	
	
	
	
	

}
