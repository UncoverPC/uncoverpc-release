package com.uncoverpc.product;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {

	@JsonProperty("iframe")
	private String iframe;
	@JsonProperty("link")
	private String link;
	@JsonProperty("price")
	private String price;
	@JsonProperty("imageLink")
	private String imageLink;
	@JsonProperty("title")
	private String title;
	@JsonProperty("productAnswer")
	private Map<String, Object> answerBank = new HashMap<String, Object>();
	
	public Product(String iframe, String link, String price, String imageLink, String title,
			Map<String, Object> answerBank) {
		super();
		this.iframe = iframe;
		this.link = link;
		this.price = price;
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
	@Override
	public String toString() {
		return "Answers [iframe=" + iframe + ", link=" + link + ", price=" + price + ", imageLink=" + imageLink
				+ ", title=" + title + ", answerBank=" + answerBank + "]";
	}
	
}
