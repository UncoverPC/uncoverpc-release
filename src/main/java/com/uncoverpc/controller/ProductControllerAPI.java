package com.uncoverpc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.uncoverpc.db.QuizProductService;
import com.uncoverpc.product.Product;

@Controller("/api/addProduct")
@ResponseBody
public class ProductControllerAPI {

//	@Autowired
//	QuizProductService quizProductService;

//	@PostMapping("/{quiz}")
//	public ResponseEntity<?> addProduct(@PathVariable String quiz, @RequestBody Product product) {
//		try {
//			quizProductService.save(product); //TO DO add for different quizes
//		} catch (Exception e) {
//
//		}
//		return ResponseEntity.status(HttpStatus.OK).build();
//	}
	

}
