package com.neoalgoritma.util;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Utils {
	
	CodecRegistry pojoCodecRegistry;
	
	public Utils() {
		pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}
	
	public int generateUniqueId(MongoDatabase database,String collectionName ) {
		int id = 0;
		long countCol;
		
		MongoCollection<Document> myCollection = database.getCollection(collectionName);
		countCol = myCollection.countDocuments();
		id = Math.toIntExact(countCol);

		Document document = myCollection.find(eq("_id", id)).first();
		while (document != null || id == 0) {
			id = id+1;
			document = myCollection.find(eq("_id", id)).first();
		} 
		//System.out.println(document == null);
		return id;
	}
	
	public boolean isValidEmailAddress(String enteredEmail) {
		
		//String EMAIL_REGIX = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";
	    //Pattern pattern = Pattern.compile(EMAIL_REGIX);
	    // Matcher matcher = pattern.matcher(enteredEmail);
	    //return matcher.matches();
		
		
		boolean isValid = false;
		//boolean containsDot = false;
		//boolean containsAt = false;
		int dotPosition = 0;
		int addPosition = 0;
		//char addSign = '@';
		char dotLetter = '.';
		
		if (enteredEmail != null && !enteredEmail.isEmpty()){
			//if(enteredEmail.contains(".")) containsDot = true;
			//if(enteredEmail.contains("@")) containsAt = true;
			dotPosition = enteredEmail.lastIndexOf(".");
			addPosition = enteredEmail.lastIndexOf("@");
			if(dotPosition != -1 && addPosition != -1 && dotPosition != 0 && addPosition != 0 && dotPosition != (enteredEmail.length()-1) 
					&& addPosition != (enteredEmail.length()-1) && dotPosition != (addPosition + 1) 
					&& dotPosition != (addPosition -1) && addPosition != (dotPosition + 1) && addPosition != (dotPosition - 1)
					&& enteredEmail.indexOf(addPosition+1) != dotLetter && enteredEmail.indexOf(addPosition-1) != dotLetter
					){
					isValid = true;
			}
		}
		return isValid;
	} 
	
	
	

}
