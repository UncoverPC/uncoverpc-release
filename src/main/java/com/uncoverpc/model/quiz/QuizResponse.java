package com.uncoverpc.model.quiz;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizResponse {
	@JsonProperty("questions")
	private ArrayList<String> questions = new ArrayList<String>();
	@JsonProperty("answers")
	private ArrayList<String> answers = new ArrayList<String>();

	
	public QuizResponse(ArrayList<String> questions, ArrayList<String> answers) {
		super();
		this.questions = questions;
		this.answers = answers;
	}
	
	public ArrayList<String> getQuestions() {
		return questions;
	}
	public void setQuestions(ArrayList<String> questions) {
		this.questions = questions;
	}
	public ArrayList<String> getAnswers() {
		return answers;
	}
	@Override
	public String toString() {
		return "QuizResponse [questions=" + questions + ", answers=" + answers + "]";
	}

	public void setAnswers(ArrayList<String> answers) {
		this.answers = answers;
	}
	
	
}
