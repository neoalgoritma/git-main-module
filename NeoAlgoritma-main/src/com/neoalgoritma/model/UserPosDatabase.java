package com.neoalgoritma.model;

import org.bson.types.ObjectId;

public class UserPosDatabase {

	public ObjectId id;
	public String databaseName;
	public String databasePassword;
	public ObjectId userId;
	
	public UserPosDatabase() {
		
	}

	public UserPosDatabase(ObjectId userId) {
		super();
		this.userId = userId;
	}

	public UserPosDatabase(ObjectId id, String databaseName, String databasePassword, ObjectId userId) {
		super();
		this.id = id;
		this.databaseName = databaseName;
		this.databasePassword = databasePassword;
		this.userId = userId;
	}

	public UserPosDatabase(String databaseName, String databasePassword, ObjectId userId) {
		super();
		this.databaseName = databaseName;
		this.databasePassword = databasePassword;
		this.userId = userId;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
