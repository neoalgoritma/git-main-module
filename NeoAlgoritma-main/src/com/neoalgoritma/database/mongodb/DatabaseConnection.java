package com.neoalgoritma.database.mongodb;

import java.util.Arrays;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnection {

	String user = "mongoroot";     // the user name
	String source = "admin";   // the source where the user is defined
	char[] password = "mongoroot123".toCharArray();
	String connectionURL;
	int port;
	
	MongoClient mongoClient = null;
	MongoDatabase userDatabase = null;
	MongoCredential credential;
	
	String databaseName = "";
	boolean databaseExist = false;
	MongoCursor<String> databaseCursor = null;
	
	public DatabaseConnection(String connectionURL,int port,String databaseName) {
		
		this.connectionURL = connectionURL;
		this.port = port;
		this.databaseName = databaseName;
		
		try {
			
			openMongoClient();
			
		} catch (Exception e) {
			System.out.println("Error connection to mongodb server " + connectionURL+ ":" + port);
			e.printStackTrace();
		}
		
		finally {

			if(mongoClient == null) {
				System.out.println("Unable to connect to mongodb server");
			}
			else {
				
				System.out.println("Connected to MongoDB server at " + connectionURL + ":" + port);
				//this.databaseName = databaseName;
				checkDatabaseExist();
				getDatabase();
				
			}
		
		}
	
	}
	
	public boolean checkDatabaseExist() {		
		MongoCursor<String> databaseCursor = null;
		try {
			databaseCursor =  mongoClient.listDatabaseNames().iterator();
			while (databaseCursor.hasNext()) {
				if(databaseCursor.next().equalsIgnoreCase(databaseName)){
					databaseExist = true;
					System.out.println("Database found:" + databaseName);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("Error while checking database exists -- DatabaseConnection.java");
			e.printStackTrace();
		} finally {
			closeDatabaseCursor();
		}
		
		return databaseExist;
	}
	
	public void getDatabase() {
		if (mongoClient != null) {
			System.out.println("connecting to database: " + databaseName);
			userDatabase = mongoClient.getDatabase(databaseName);
			System.out.println("Created database: " + userDatabase.getName());
		}
	}
	
	public void openMongoClient() {
		//System.out.println("creating mongoClient: " + mongoClient);
		if(mongoClient == null) {
			//mongoClient = new MongoClient();
			credential = MongoCredential.createCredential(user, source, password);
			mongoClient =  MongoClients.create(
		        MongoClientSettings.builder()
                .applyToClusterSettings(builder -> 
                        builder.hosts(Arrays.asList(new ServerAddress(connectionURL, port))))
                .credential(credential)
                .build());
			checkDatabaseExist();
			getDatabase();
		}
	}
	public void closeMongoClient() {
		if (mongoClient != null){
			mongoClient.close();
			mongoClient = null;
		}
	}
	
	public void closeDatabaseCursor() {
		if(databaseCursor != null) {
			databaseCursor.close();
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public MongoCredential getCredential() {
		return credential;
	}

	public void setCredential(MongoCredential credential) {
		this.credential = credential;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public boolean isDatabaseExist() {
		return databaseExist;
	}

	public void setDatabaseExist(boolean databaseExist) {
		this.databaseExist = databaseExist;
	}

	public MongoCursor<String> getDatabaseCursor() {
		return databaseCursor;
	}

	public void setDatabaseCursor(MongoCursor<String> databaseCursor) {
		this.databaseCursor = databaseCursor;
	}

	public MongoDatabase getUserDatabase() {
		return userDatabase;
	}

	public void setUserDatabase(MongoDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
