package com.uncoverpc.quiz;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Answer {
	
	private Map<String, Integer> answers = new HashMap<String, Integer>();

	public Answer(Map<String, Integer> answers) {
		super();
		this.answers = answers;
	}

	public Answer() {
		super();
	}

	public Map<String, Integer> getAnswers() {
		return answers;
	}

	public void setAnswers(Map<String, Integer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Answer [answers=" + answers + "]";
	}
	
}
