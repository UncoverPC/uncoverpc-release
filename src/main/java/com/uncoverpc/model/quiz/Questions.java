package com.uncoverpc.model.quiz;

import java.util.ArrayList;

public class Questions {

	private ArrayList<Question> questions = new ArrayList<Question>();

	public Questions(ArrayList<Question> questions) {
		super();
		this.questions = questions;
	}

	public Questions() {
		super();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Questions [questions=" + questions + "]";
	}
	
	
	
}
