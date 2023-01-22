package com.uncoverpc.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Document("laptops")
public class Laptop {
	
	@Id
	private String id;
	@JsonProperty("Source")
	private String source;
	@JsonProperty("Name")
	private String name;
	@JsonProperty("Model")
	private String model;
	@JsonProperty("Link")
	private String link;
	@JsonProperty("Img")
	private String img;
	@JsonProperty("Price")
	private String price;
	@JsonProperty("Properties")
	private ArrayList<ArrayList<String>> properties;
	@JsonProperty("Extras")
	private ArrayList<String> extras;
	@JsonProperty("QuizResponses")
	private JsonNode quizResponses;
	//private HashMap<String, String> quizResponses;
	
	public Laptop(String id, String model, String source, String img, String name, String link, String price, ArrayList<ArrayList<String>> properties, ArrayList<String>extras, JsonNode quizResponses) {
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
		this.quizResponses = quizResponses;//new ObjectMapper().convertValue(quizResponses, new TypeReference <HashMap <String, Object>>(){});
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

//	public void setProperties(JsonNode properties) {
//		this.properties = new ObjectMapper().convertValue(properties, new TypeReference <HashMap <String, Object>>(){});
//	}
//
//	public Map<String, Object> getQuizResponses() {
//		return quizResponses;
//	}
//
//	public void setQuizResponses(JsonNode quizResponses) {
//		this.quizResponses = new ObjectMapper().convertValue(quizResponses, new TypeReference <HashMap <String, Object>>(){});
//	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
}