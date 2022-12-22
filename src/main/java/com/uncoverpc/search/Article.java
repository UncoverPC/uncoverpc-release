package com.uncoverpc.search;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

@Document (indexName = "articles")
public class Article {
	
	@Id
	private String id;
	
	private String title;
	
	public Article() {
		
	}
	
	public Article(String title, List<String> contents) {
		this.title = title;
		this.contents = contents;
	}
	
	@Field( includeInParent=true)
	private List<String> contents;
	
	public String getId() {
		return this.id;
	}
	public String getTitle() {
		return this.title;
	}
	public List<String> getContents() {
		return this.contents;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(List<String> contents) {
		this.contents = contents;
	}
	public void addContent(String content) {
		this.contents.add(content);
	}

}
