package com.neoalgoritma.model;

import org.bson.types.ObjectId;

public class NeoAlgoritmaPosUserDatabase {

	public ObjectId id;
	public String databaseName;
	public String databasePassword;
	public String companyName;
	public ObjectId userId;
	
	public NeoAlgoritmaPosUserDatabase() {
		
	}

	public NeoAlgoritmaPosUserDatabase(ObjectId id, String databaseName, String databasePassword, String companyName, ObjectId userId) {
		this.id = id;
		this.databaseName = databaseName;
		this.databasePassword = databasePassword;
		this.companyName = companyName;
		this.userId = userId;
	}

	public NeoAlgoritmaPosUserDatabase(String databaseName, String databasePassword,
			String companyName, ObjectId userId) {
		
		this.databaseName = databaseName;
		this.databasePassword = databasePassword;
		this.companyName = companyName;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "NeoAlgoritmaPosSetting [id=" + id + ", databaseName=" + databaseName + ", databasePassword="
				+ databasePassword + ", companyName=" + companyName
				+ ", userId=" + userId + "]";
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
