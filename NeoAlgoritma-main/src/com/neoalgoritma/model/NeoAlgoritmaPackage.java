package com.neoalgoritma.model;

import org.bson.types.ObjectId;

public class NeoAlgoritmaPackage {
	
	public ObjectId id;
	public String name;
	public String description;
	public float price;
	public int tenure;
	
	
	public NeoAlgoritmaPackage() {
		
	}
	
	
	
	public NeoAlgoritmaPackage(String name, String description, float price, int tenure) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.tenure = tenure;
	}



	public NeoAlgoritmaPackage(ObjectId id, String name, String description, float price,int tenure) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.tenure = tenure;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	@Override
	public String toString() {
		return "Package [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", tenure=" + tenure + "]";
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
