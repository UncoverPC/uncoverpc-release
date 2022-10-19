package com.uncoverpc.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uncoverpc.db.QuizProductService;
//import com.uncoverpc.db.QuizProductService;
import com.uncoverpc.product.Product;

@Controller("/api/addProduct")
@ResponseBody
public class ProductControllerAPI {

	@Autowired
	QuizProductService quizProductService;
	
	@PostMapping("/productquiz/addProduct/{quizName}")
	@ResponseBody
	public ResponseEntity<Object> addProductToQuizDB(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "quizName") String quizName, @RequestBody Product product) {
		try {
			product.setReferenceQuiz(quizName);
			return new ResponseEntity<>(quizProductService.save(product), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
