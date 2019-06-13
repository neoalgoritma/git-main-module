package com.neoalgoritma.dao;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;

import com.neoalgoritma.model.Subscription;

public class SubscriptionDAO extends GenericDAO<Subscription> implements Serializable {

	private static final long serialVersionUID = 1L;
	private NeoAlgoritmaPackageDAO neoAlgoritmaPackageDAO = new NeoAlgoritmaPackageDAO("neoalgoritma","package");

	public SubscriptionDAO(String databaseName, String collectionName) {
		super(databaseName, collectionName);
		// TODO Auto-generated constructor stub
	}

	public boolean subscribed(List<Subscription> subscribed,ObjectId packageId) {
		
		for (Subscription subscription : subscribed) {
			if(subscription.getPackageId() == packageId) return true;
		}
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
