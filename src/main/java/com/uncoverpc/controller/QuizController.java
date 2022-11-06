package com.uncoverpc.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Question;
import com.uncoverpc.model.quiz.Quiz;

@Controller
public class QuizController {

	@Autowired
	QuizService quizService;


	@GetMapping("/productquiz/{quizName}")
	public ModelAndView getQuiz(@PathVariable(value = "quizName") String quizName) {
		ModelAndView model = new ModelAndView("quiz.html");
		Quiz quiz = quizService.findByQuizTitle(quizName);
		model.addObject("questions", quiz.getQuestions());
		model.addObject("quizTitle", quiz.getTitle());
		return model;
	}

	@GetMapping("/quizCreateSuccess.html")
	public ModelAndView success() {
		ModelAndView model = new ModelAndView("quizCreateSuccess.html");
		return model;
	}

	@GetMapping("/create_quiz1")
	public ModelAndView testQuiz() {
		ModelAndView model = new ModelAndView("create_quiz1.html");
		return model;
	}

	@PostMapping("/api/quiz/create")
	public ModelAndView addQuiz (HttpServletRequest request, @RequestBody Quiz quiz) {
		System.out.println("Trying to add quiz");
		System.out.println("quiz: "+quiz.toString());
		try {
			quizService.save(quiz);
			ModelAndView view = new ModelAndView("/quizCreateSuccess.html");
			return view;
		}
		catch(Exception e) {
			ModelAndView view = new ModelAndView("/create_quiz1.html");
			return view;
		}
	}

//	{
//	    "quizTitle":"Laptop Quiz",
//	    "quizId":"90328hf09fehs0d0hfw0893",
//	    "question_bank":{
//	        "title":"Laptop",
//	        "answers":[
//	            "1h","4h","8h","10h"
//	        ]
//	    }
//	}

}
