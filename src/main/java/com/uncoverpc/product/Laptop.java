package com.uncoverpc.product;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("laptops")
public class Laptop {
	
	private String id;
	private String name;
	private Specs specs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Specs getSpecs() {
		return specs;
	}
	public void setSpecs(Specs specs) {
		this.specs = specs;
	}
	public Laptop(String id, String name, Specs specs) {
		super();
		this.id = id;
		this.name = name;
		this.specs = specs;
	}
	
	
}
