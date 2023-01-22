package com.uncoverpc.product;

import java.util.HashMap;

import javax.json.Json;

import org.springframework.data.mongodb.core.mapping.Document;

import com.google.gson.Gson;


@Document("Products")
public class Laptop {
	private String id;
	
	private String source;
	private String name;
	private String link;
	private String img;
	private float price;
	private Object properties;
	private HashMap<String, String> quizResponses;
	
	public Laptop(String id, String source, String img, String name, String link, float price, Object properties, Object quizResponses) {
		super();
		this.id = id;
		this.source = source;
		this.img = img;
		this.name = name;
		this.link = link;
		this.price = price;
		this.properties = properties;
		this.quizResponses = new Gson().fromJson(quizResponses.toString(), HashMap.class);
	}
	
	@Override
	public String toString() {
		return "Laptop [id=" + id + ", source=" + source + ", name=" + name + ", link=" + link + ", img=" + img
				+ ", price=" + price + ", properties=" + properties + ", quizResponses=" + quizResponses + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Object getProperties() {
		return properties;
	}

	public void setProperties(Object properties) {
		this.properties = properties;
	}

	public HashMap<String, String> getQuizResponses() {
		return quizResponses;
	}

	public void setQuizResponses(HashMap<String, String> quizResponses) {
		this.quizResponses = quizResponses;
	}
	
}