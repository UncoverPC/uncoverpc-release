package com.uncoverpc.product;

import java.util.HashMap;
import java.util.Map;

import javax.json.Json;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;


@Document("laptops")
public class Laptop {
	private String id;
	
	private String source;
	private String name;
	private String model;
	private String link;
	private String img;
	private String price;
	private Map<String, String> properties;
	private Map<String, String> quizResponses;
	//private HashMap<String, String> quizResponses;
	
	public Laptop(String id, String model, String source, String img, String name, String link, String price, String properties, String quizResponses) {
		super();
		this.id = id;
		this.model=model;
		this.source = source;
		this.img = img;
		this.name = name;
		this.link = link;
		this.price = price;
		this.properties = properties;
		this.quizResponses = quizResponses;//new Gson().fromJson(quizResponses.toString(), HashMap.class);
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Object getProperties() {
		return properties;
	}

	public void setProperties(JsonNode properties) {
		this.properties = properties;
	}

	public JsonNode getQuizResponses() {
		return quizResponses;
	}

	public void setQuizResponses(JsonNode quizResponses) {
		this.quizResponses = quizResponses;
	}
	
}