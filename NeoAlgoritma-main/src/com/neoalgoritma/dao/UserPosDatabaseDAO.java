package com.neoalgoritma.dao;

import org.bson.Document;
import org.bson.types.ObjectId;
import com.neoalgoritma.model.UserPosDatabase;

public class UserPosDatabaseDAO extends GenericDAO<UserPosDatabase> {

	public UserPosDatabaseDAO(String databaseName, String collectionName) {
		super(databaseName, collectionName);
		// TODO Auto-generated constructor stub
	}
	
	public String generateDatabaseName(ObjectId userId) {
		return "user_database_"+ userId.toString();
	}

	public String generateDatabasePassword(ObjectId userId) {
		return new ObjectId().toString() + userId.toString();
	}

	public UserPosDatabase findUserDatabaseSetting(ObjectId userId) {
		Document query = new Document();
		query.put("userId", userId);
		System.out.println("findUserDatabase Query: " + query);
		return findDocument(query);
	}

	@Override
	public UserPosDatabase insert(UserPosDatabase entity) {
		
		return super.insert(entity);
	}


/*
	public boolean databaseNameTaken(String databaseName,ObjectId userId) {
		
		boolean registered = false;
		Document query = new Document();
		query.append("$and", Arrays.asList(
                new Document()
                        .append("databaseName", databaseName),
                new Document()
                        .append("userId", new BasicDBObject("$ne", userId))
            ));
		System.out.println("databaseNameTaken query: " + query);
		if(findDocument(query) != null) { 
			registered = true;
		}
		return registered;
	}

*/
	










}
