package com.uncoverpc.model.quiz;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {

	@JsonProperty("title")
	private String question;
	@JsonProperty("scalable")
	private boolean scalable;
	@JsonProperty("answers")
	private ArrayList<String> answers = new ArrayList<String>();
	@JsonProperty("identifer")
	private String identifier;
	@JsonProperty("keywords")
	private ArrayList<String> keywords = new ArrayList<String>();
	private String imageLink;
	
	public Question(String question, boolean scalable, ArrayList<String> answers, String identifier, ArrayList<String> keywords, String imageLink) {
		super();
		this.question = question;
		this.scalable = scalable;
		this.answers = answers;
		this.identifier = identifier;
		this.keywords = keywords;
		this.imageLink = imageLink;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public ArrayList<String> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}

	public boolean isScalable() {
		return scalable;
	}

	public void setScalable(boolean scalable) {
		this.scalable = scalable;
	}
	public String getIdentifier() {
		return this.identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public ArrayList<String> getKeywords() {
		return this.keywords;
	}
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
	public String getImageLink() {
		return this.imageLink;
	}
	public void setImageLink(String link) {
		this.imageLink = link;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", scalable=" + scalable + ", answers="
				+ answers + "]";
	}
	
	
	
}
