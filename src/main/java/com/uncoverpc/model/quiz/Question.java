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
	
	public Question(String question, boolean scalable, ArrayList<String> answers) {
		super();
		this.question = question;
		this.scalable = scalable;
		this.answers = answers;
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

	@Override
	public String toString() {
		return "Question [question=" + question + ", scalable=" + scalable + ", answers="
				+ answers + "]";
	}
	
	
	
}
