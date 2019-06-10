package com.neoalgoritma.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.bson.types.ObjectId;

import com.neoalgoritma.dao.NeoAlgoritmaPackageDAO;

public class Subscription {

	public ObjectId id; 
	public ObjectId packageId;
	public LocalDateTime startDate;
	public LocalDateTime endDate;
	public ObjectId userId;
	
	
	public Subscription() {
	}

	public Subscription(ObjectId id, ObjectId packageId, LocalDateTime startDate, LocalDateTime endDate,
			ObjectId userId) {
		
		this.id = id;
		this.packageId = packageId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		
		
	}

	public Subscription(ObjectId packageId, LocalDateTime startDate, LocalDateTime endDate, ObjectId userId) {
		this.packageId = packageId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
		
	}

	public NeoAlgoritmaPackage getPackageDetail() {
		return new NeoAlgoritmaPackageDAO("neoalgoritma","package").findOneById(this.packageId.toString());
	}
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getPackageId() {
		return packageId;
	}

	public void setPackageId(ObjectId packageId) {
		this.packageId = packageId;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", packageId=" + packageId + ", startDate=" + startDate + ", endDate="
				+ endDate + ", userId=" + userId + "]";
	}

	
	
}