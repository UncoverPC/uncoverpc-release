package com.uncoverpc.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.Binary;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Document(collection = "laptops")
public class Laptop {
	
	@Id
	private Binary id;
	
	@Field("Source")
	private String source;
	
	@Field("Name")
	private String name;
	
	@Field("Model")
	private String model;
	
	@Field("Link")
	private String link;
	
	@Field("Img")
	private String img;
	
	@Field("Price")
	private String price;
	
	@Field("Properties")
	private ArrayList<String> properties;
	
	@Field("Extras")
	private ArrayList<Object> extras;
	
	@Field("QuizResponses")
	private Map<String, Object> quizResponses;
	//private HashMap<String, String> quizResponses;
	
	public Laptop(Binary id, String model, String source, String img, String name, String link, String price, ArrayList<String> properties, ArrayList<Object>extras, @JsonProperty("QuizResponses")Map<String, Object> quizResponses) {
		super();
		this.id = id;
		this.model = model;
		this.source = source;
		this.img = img;
		this.name = name;
		this.link = link;
		this.price = price;
		this.properties = properties;
		this.extras = extras;
		this.quizResponses = quizResponses;
	}
	
	@Override
	public String toString() {
		return "Laptop [id=" + id + ", source=" + source + ", name=" + name + ", link=" + link + ", img=" + img
				+ ", price=" + price + ", properties=" + properties + ", quizResponses=" + quizResponses + "]";
	}

	public Binary getId() {
		return id;
	}

	public void setId(Binary id) {
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

	public ArrayList<String> getProperties() {
		return properties;
	}

	public Map<String, Object> getQuizResponses() {
		return quizResponses;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}