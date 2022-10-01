package com.uncoverpc.model.quiz;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.uncoverpc.db.QuizService;
import com.uncoverpc.product.Product;

public class Quiz {

	
	@JsonProperty("question_bank")
	private ArrayList<Question> questions = new ArrayList<Question>();
	
	@JsonProperty("quizTitle")
	private String title;
	
	@Autowired
	private QuizService quizService;
	
//	private ArrayList<Product> searchRecommendedProduct(){
//		
//	}
	
	
	
	public Object loadQuiz(String quizName) {
		return quizService.getQuiz(quizName);
	}

	public Quiz(ArrayList<Question> questions, String title, QuizService quizService) {
	super();
	this.questions = questions;
	this.title = title;
	this.quizService = quizService;
}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Question> questions) {
		this.questions = questions;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public QuizService getQuizService() {
		return quizService;
	}

	public void setQuizService(QuizService quizService) {
		this.quizService = quizService;
	}

	@Override
	public String toString() {
		return "Quiz [questions=" + questions + ", title=" + title + ", quizService=" + quizService + "]";
	}
	
	
	
}
