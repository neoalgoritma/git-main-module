package com.neoalgoritma.util;

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

public class CreateNeoAlgoritmaPackage {

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
			System.out.println("Packages created");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		
	}

}
