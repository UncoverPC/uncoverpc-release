package com.uncoverpc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.cloud.firestore.DocumentSnapshot;
import com.uncoverpc.db.QuizProductService;
import com.uncoverpc.db.QuizService;
import com.uncoverpc.model.quiz.Quiz;
import com.uncoverpc.model.quiz.QuizResponseAnswer;
import com.uncoverpc.product.Product;

@Controller
public class MainController {

	@Autowired
	private QuizProductService quizProductService;
	
	@Autowired
	private QuizService quizService;

	@GetMapping("/home")
	public String home() {
		return "index.html";
	}

	@GetMapping("/productquiz/{quizName}")
	public ModelAndView testQuiz(@PathVariable(value = "quizName") String quizName) {
		ModelAndView model = new ModelAndView("quiz_template.html");
		System.out.println(quizName);
		DocumentSnapshot document = (DocumentSnapshot) quizService.getQuiz(quizName);
		if (document.get("questions") == null) {
			return new ModelAndView("quizNotFound.html");
		}
		model.addObject("questions", document.get("questions"));

		model.addObject("quizTitle", document.getId());
		return model;
	}

	@GetMapping("/productquiz/addProduct/{quizName}")
	public ModelAndView addProductToQuiz(@PathVariable(value = "quizName") String quizName) {
		ModelAndView model = new ModelAndView("addProductToQuiz.html");
		System.out.println(quizName);
		DocumentSnapshot document = (DocumentSnapshot) quizService.getQuiz(quizName);
		if (document.get("questions") == null) {
			return new ModelAndView("addProductToQuiz.html.html");
		}
		model.addObject("questions", document.get("questions"));

		model.addObject("quizTitle", document.getId());
		return model;
	}

	@PostMapping("/productquiz/addProduct/{quizName}")
	@ResponseBody
	public ResponseEntity<Object> addProductToQuizDB(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "quizName") String quizName, @RequestBody Product product) {
		try {
			System.out.println(product);
			return new ResponseEntity<>(quizProductService.addProduct(quizName, product), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/api/quiz/retrive/laptop")
	@ResponseBody
	public Object retriveLaptopQuiz() {
		return quizService.getQuiz("Laptop Recommendation Quiz");
//		return "HELLO";
	}

	@PostMapping("/api/quiz/create/")
	@ResponseBody
	public String postQuiz(HttpServletRequest request, @RequestBody Quiz quiz) throws IOException {
		System.out.println(quiz.toString());
		try {
			return quizService.createQuiz(quiz);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Unexpected Error";
	}
	
	@PostMapping("/productquiz/{quizName}/getResults")
	@ResponseBody
	public ResponseEntity<Object> getQuizResults(HttpServletRequest request, HttpServletResponse response, @RequestBody ArrayList<QuizResponseAnswer> answer) {
		System.out.println(answer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/admin/Dashboard")
	@ResponseBody
	public String adminDashbaord() {
		return "under development";
	}
	
	@PostMapping("/search")
	@ResponseBody
	public ResponseEntity<Object> getQuizResults(HttpServletRequest request, HttpServletResponse response, @RequestBody String query) {
		System.out.println(query);
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	
	}
	
	@GetMapping("/admin/createQuiz")
	public String createQuiz() {
		return "create_quiz.html";
	}
	
	@GetMapping("/explore")
	public String explorePage() {
		return "explore.html";
	}
}
