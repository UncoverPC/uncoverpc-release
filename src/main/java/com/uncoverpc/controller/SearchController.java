package com.uncoverpc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Quiz;

@Controller("api")
public class SearchController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("/search")
	public List<Quiz> getQuiz(@RequestBody String quiz){
		//TO DO get matched quizzes
		return quizService.findAll();
	}
	
}
