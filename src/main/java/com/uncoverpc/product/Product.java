package com.uncoverpc.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document("products")
public class Product {

	@JsonProperty("iframe")
	private String iframe;
	@JsonProperty("link")
	private String link;
	@JsonProperty("price")
	private String price;
	@JsonProperty("referenceQuiz")
	private String referenceQuiz;
	@JsonProperty("imageLink")
	private String imageLink;
	@JsonProperty("title")
	private String title;
	@JsonProperty("productAnswer")
	private Map<String, Object> answerBank = new HashMap<String, Object>();
	
	public Product(String iframe, String link, String price, String referenceQuiz, String imageLink, String title,
			Map<String, Object> answerBank) {
		super();
		this.iframe = iframe;
		this.link = link;
		this.price = price;
		this.referenceQuiz = referenceQuiz;
		this.imageLink = imageLink;
		this.title = title;
		this.answerBank = answerBank;
	}
	public Product() {
		super();
	}
	public String getIframe() {
		return iframe;
	}
	public void setIframe(String iframe) {
		this.iframe = iframe;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Map<String, Object> getAnswerBank() {
		return answerBank;
	}
	public void setAnswerBank(Map<String, Object> answerBank) {
		this.answerBank = answerBank;
	}
	
	
	public String getReferenceQuiz() {
		return referenceQuiz;
	}
	public void setReferenceQuiz(String referenceQuiz) {
		this.referenceQuiz = referenceQuiz;
	}
	@Override
	public String toString() {
		return "Product [iframe=" + iframe + ", link=" + link + ", price=" + price + ", referenceQuiz=" + referenceQuiz
				+ ", imageLink=" + imageLink + ", title=" + title + ", answerBank=" + answerBank + "]";
	}
	
	
}
