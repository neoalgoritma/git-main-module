package com.neoalgoritma.dao;

import java.util.Arrays;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.neoalgoritma.model.NeoAlgoritmaPosUserDatabase;

public class NeoAlgoritmaPosUserDatabaseDAO extends GenericDAO<NeoAlgoritmaPosUserDatabase> {

	public NeoAlgoritmaPosUserDatabaseDAO(String databaseName, String collectionName) {
		super(databaseName, collectionName);
		// TODO Auto-generated constructor stub
	}
	
	
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

	public NeoAlgoritmaPosUserDatabase findUserDatabaseSetting(ObjectId userId) {
		Document query = new Document();
		query.put("userId", userId);
		System.out.println("findUserDatabase Query: " + query);
		return findDocument(query);
	}
}
