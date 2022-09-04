package com.uncoverpc.quiz;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuizResponseAnswer {
	
	@JsonProperty("current_question")
	private String answers;
	
	@JsonProperty("answer")
	private String answer;

	public QuizResponseAnswer(String answers, String answer) {
		super();
		this.answers = answers;
		this.answer = answer;
	}
	public QuizResponseAnswer() {
		
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String toString() {
		return "Answer [current question=" + answers + ", answer="
				+ answer + "]"; 
	}
}
