package com.neoalgoritma.user;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.BSONObject;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.neoalgoritma.database.mongodb.DatabaseConnection;
import com.neoalgoritma.util.Config;

public abstract class GenericDAO<T> {

	private Class<T> entityClass;
	DatabaseConnection dbConn;
	MongoDatabase database;
	CodecRegistry pojoCodecRegistry;
	public String databaseName;
	public String collectionName;
	MongoCollection<T> collection;
	
	@SuppressWarnings("unchecked")
	public GenericDAO(String databaseName,String collectionName) {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.databaseName = databaseName;
		this.collectionName = collectionName;
		
		Config cfg = new Config();
		dbConn = new DatabaseConnection(cfg.getProperty("dbURL"), Integer.parseInt(cfg.getProperty("dbPort")),this.databaseName);
		database = dbConn.getUserDatabase();
		pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		collection = database.getCollection(this.collectionName, entityClass);
		collection = collection.withCodecRegistry(pojoCodecRegistry);

		System.out.println("Constructed GenericDao, with class: " + entityClass + " database: " + this.databaseName + " collection: " + this.collectionName);
	}

	public T insert(T entity) {
		try {
			
			collection.insertOne(entity);
			System.out.println("Inserted " + entity.toString() + "to database: " + this.databaseName + " collection: " + this.collectionName);
			return entity;
			
		} catch (Exception e) {
			
			return null;
		}
	}
	
	@SuppressWarnings({ "hiding", "unchecked" })
	public T findOneById(String id) {
		Object returnValue = null;
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id));
		returnValue = collection.find(query,entityClass).first();
		return (T) returnValue;
	}
	
	public boolean updateOneById(String id,T newValue) {
		T updatedDocument;
		BasicDBObject oldDocument = new BasicDBObject();
		BasicDBObject newDocument = new BasicDBObject();
		//UpdateResult updateResult;
		boolean updated = false;
		try {
			oldDocument.put("_id", new ObjectId(id));
			newDocument.put("$set", newValue);
			updatedDocument = collection.findOneAndUpdate(oldDocument, newDocument); 
			//updateResult = collection.updateOne(oldDocument0 newDocument,(new UpdateOptions()).upsert(true));
			//System.out.println(test);
			if(updatedDocument != null) {
				updated = true;
				System.out.println("Update successful document id: " + id);
			}
			else {
				System.out.println("Update failed no document found with id: " + id);
			}
			
		} catch (Exception e) {
			System.out.println("Error while update id: " + id);
			e.printStackTrace();
			updated = false;
		}	
		
		return updated;
	}
	
	public boolean delete(String id) {
		boolean deleted = false;
	
		try {
			BasicDBObject query = new BasicDBObject();
		    query.put("_id", new ObjectId(id));
			collection.deleteOne(query);
			deleted = true;
		} catch (Exception e) {
			System.out.println("Error while deleting id: " + id);
		}
		return deleted;
	}


	@SuppressWarnings("unchecked")
	public T findDocument(Document query) {
		Object returnValue = null;
		returnValue = collection.find(query,entityClass).first();
		System.out.println(query);
		return (T) returnValue;

	}

	public List<T> findDocument(Document query,Document sort,int pageSize,int offset){
		
		List<T> returnValue = new ArrayList<T>();
		System.out.println("query: " + query);
		//List example = new ArrayList<>();
		returnValue = collection.find(query,(entityClass)).sort(sort).skip(offset).limit(pageSize).into(new ArrayList<>());
		
		//System.out.println(example.size());
		//for (Object object : example) {
			//System.out.println(object);
		//}
		 
		 
		return returnValue;
	}
}
