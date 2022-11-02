package com.uncoverpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.firestore.DocumentSnapshot;
import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Quiz;

@Controller
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@GetMapping("/productquiz/{quizName}")
	public ModelAndView getQuiz(@PathVariable(value = "quizName") String quizName) {
		ModelAndView model = new ModelAndView("quiz_template.html");
		Quiz quiz = quizService.findByQuizTitle(quizName);
		model.addObject("questions", quiz.getQuestions());

		model.addObject("quizTitle", quiz.getTitle());
		return model;
	}
	
	@GetMapping("admin/createQuiz")
	public ModelAndView testQuiz() {
		ModelAndView model = new ModelAndView("create_quiz1.html");
		return model;
	}
	
	
}
