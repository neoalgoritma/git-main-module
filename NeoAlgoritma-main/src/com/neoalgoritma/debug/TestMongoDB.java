package com.neoalgoritma.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.neoalgoritma.dao.NeoAlgoritmaPackageDAO;
import com.neoalgoritma.dao.SubscriptionDAO;
import com.neoalgoritma.dao.UserDAO;
import com.neoalgoritma.model.Address;
import com.neoalgoritma.model.NeoAlgoritmaPackage;
import com.neoalgoritma.model.User;

public class TestMongoDB {

	public static void main(String[] args) {
					
		try {
			
			NeoAlgoritmaPackageDAO neopackageDAO = new NeoAlgoritmaPackageDAO("neoalgoritma","package");
			NeoAlgoritmaPackage neopackage = new NeoAlgoritmaPackage("Basic Point of Sales","Provide basic point of sales system. Useful for small retail business owner who "
					+ "requires basic point of sales system",0,0);
			neopackageDAO.insert(neopackage);
			neopackage = new NeoAlgoritmaPackage("Starter Point of Sales","Provide more point of sales system. Useful small retail business owner who " + 
					"requires more point of sales system. The system will be able to provide database download etc " + 
					"blah blah blah blah",10,30);
			neopackageDAO.insert(neopackage);
			//UserDAO userDAO = new UserDAO("neoalgoritma","user");
			//User user = new User();
			//ObjectId o = new ObjectId();
			//System.out.println(userDAO.getUserLogin("Email2", "password2"));
			//Test findMany
			/*
			Document query = new Document();
			query.append("$or", Arrays.asList(
                    new Document()
                            .append("email", "email2"),
                    new Document()
                            .append("email", "email3")
                )
            );
			query.append("email", "email2");
			query.append("password", "password2");
			Document sort = new Document();
            //sort.append("email", -1.0);
            
			List<User> users = new ArrayList<User>();
			users = userDAO.findDocument(query,sort,3,0);
			System.out.println("size: " + users.size());
			for (Iterator iterator = users.iterator(); iterator.hasNext();) {
				User user2 = (User) iterator.next();
				System.out.println(user2);
			}
			System.out.println("----------------------------");
			
			//query = new Document();
			//query.append("email", "email2");
	        //query.append("password", "password");
			//userDAO.findMany(query);
			
			//Test insertOne
			//user = new User("Tan", "martin", "password", "email", "mobile", true, 
			//		new Address("gambir", "country", "postcode"));
			//userDAO.insert(user);
			
			//Test update
			//o = new ObjectId("5cfb3be77345f71927c3b63e");
			//Address address = new Address("gambir3", "country3", "postcode3");
			//user = new User("Tan2", "martin2", "password2", "email2", "mobile2", true, 
			//		address);

			//System.out.println(userDAO.updateOneById(o.toHexString(), user));
			
			
			/*
			 * Test Insert
			*/
			/*
			for (int i = 0; i < 1000000; i++) {
				Address address = new Address("gambir", "country", "postcode");
				user = new User("Tan", "martin", "password", "email", "mobile", true, 
						address);
				userDAO.insert(user);

			}
			
			System.out.println("******************************************************* End");
			*/
		
			
			// Test find
			//System.out.println(userDAO.findOneById("5cfb3be77345f71927c3b63e"));
			
			// Test delete
			
			//System.out.println(userDAO.delete("5cf4e83926983669e48dcf80"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		
	}

}
