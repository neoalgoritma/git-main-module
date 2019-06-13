package com.neoalgoritma.controller.secured;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.primefaces.PrimeFaces;

import com.neoalgoritma.dao.NeoAlgoritmaPackageDAO;
import com.neoalgoritma.dao.SubscriptionDAO;
import com.neoalgoritma.dao.UserPosDatabaseDAO;
import com.neoalgoritma.model.NeoAlgoritmaPackage;
import com.neoalgoritma.model.Subscription;
import com.neoalgoritma.model.User;
import com.neoalgoritma.model.UserPosDatabase;
import com.neoalgoritma.util.Config;

public class SubscriptionBean implements Serializable {

	private static final long serialVersionUID = 1L;
	public List<NeoAlgoritmaPackage> neoAlgoritmaPackages;
	private NeoAlgoritmaPackageDAO neoPackageDAO; 
	public List<Subscription> subscribed;
	public SubscriptionDAO subscriptionDAO;
	public User loggedUser;
	
	@PostConstruct
	public void init() {
		loggedUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedUser");
		neoPackageDAO = new NeoAlgoritmaPackageDAO("neoalgoritma", "package");
		subscriptionDAO = new SubscriptionDAO("neoalgoritma", "subscription");
		Document query = new Document();
		
		subscribed = subscriptionDAO.findDocument(query, query, 100, 0);
		neoAlgoritmaPackages = new ArrayList<NeoAlgoritmaPackage>();
		neoAlgoritmaPackages = neoPackageDAO.findDocument(query,query,100,0);
		
		System.out.println("neoPackages size:" + neoAlgoritmaPackages.size());
	}

	public List<NeoAlgoritmaPackage> getNeoAlgoritmaPackages() {
		return neoAlgoritmaPackages;
	}

	public void setNeoAlgoritmaPackages(List<NeoAlgoritmaPackage> neoAlgoritmaPackages) {
		this.neoAlgoritmaPackages = neoAlgoritmaPackages;
	}
	
	public void subscribe(NeoAlgoritmaPackage selectedPackage) {
		
		if(selectedPackage != null) {
			LocalDateTime startDate = LocalDateTime.now();
			LocalDateTime endDate = startDate;
			endDate = endDate.plusDays(selectedPackage.tenure);
			System.out.println(endDate);
			Subscription subscription = new Subscription(selectedPackage.getId(), startDate, endDate,loggedUser.getId());
			//if(subscriptionDAO.subscribed(subscribed, packageId))
			subscriptionDAO.insert(subscription);
			// create userPosDatabase if not exist
			UserPosDatabase userPosDatabase;
			UserPosDatabaseDAO userPosDatabaseDAO = new UserPosDatabaseDAO("neoalgoritma","user_database_map");
			userPosDatabase = userPosDatabaseDAO.findUserDatabaseSetting(loggedUser.getId());
			if(userPosDatabase == null) {
				userPosDatabase = new UserPosDatabase(userPosDatabaseDAO.generateDatabaseName(loggedUser.getId()),
														userPosDatabaseDAO.generateDatabasePassword(loggedUser.getId()), loggedUser.getId());
				userPosDatabaseDAO.insert(userPosDatabase);
			}
			
			PrimeFaces current = PrimeFaces.current();
			current.executeScript("redirectTo('Subscription','Added','success','pos/pos-status.xhtml');");
		}
		else {
			System.out.println("No package selected");
		}
		//Subscription subscription = new Subscription(selectedPackage.getId(), , endDate)
		//subscriptionDAO.insert(entity)
		System.out.println(selectedPackage.getId());
		
	}

	public List<Subscription> getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(List<Subscription> subscribed) {
		this.subscribed = subscribed;
	}
	
	
	
	
}
