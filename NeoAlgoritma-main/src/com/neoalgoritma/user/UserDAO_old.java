package com.neoalgoritma.user;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.neoalgoritma.database.mongodb.DatabaseConnection;
import com.neoalgoritma.util.Config;
import com.neoalgoritma.util.Utils;

public class UserDAO_old {

	
	DatabaseConnection dbConn;
	MongoDatabase database;
	CodecRegistry pojoCodecRegistry;
	
	public UserDAO_old() {
		Config cfg = new Config();
		dbConn = new DatabaseConnection(cfg.getProperty("dbURL"), 
											Integer.parseInt(cfg.getProperty("dbPort")),cfg.getProperty("dbNameWebsiteMain"));
		database = dbConn.getUserDatabase();
		pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		
	}
	
	public int insert(User user) {
		MongoCollection<User> collection = database.getCollection("user", User.class);
		collection = collection.withCodecRegistry(pojoCodecRegistry);
		int id = new Utils().generateUniqueId(database,"user");
		//user.setId(id);
		collection.insertOne(user);
		System.out.println("Inserted:" + user.toString());
		return id;
	}
	
	public List<User> getUsers(){
		
		List<User> users = new ArrayList<User>();
		MongoCollection<Document> collection = database.getCollection("user");
		collection = collection.withCodecRegistry(pojoCodecRegistry);
		Document query = new Document();
		users = collection.find(query, User.class).into(new ArrayList<>());
		
		return users;
	}
	
	
	public User findUserById(int id) {
		MongoCollection<Document> collection = database.getCollection("user");
		collection = collection.withCodecRegistry(pojoCodecRegistry);
		User document = collection.find(eq("_id", id),User.class).first();
		return document;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
