package com.uncoverpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ProductController {
	@GetMapping("/add_product")
	public ModelAndView addProduct() {
		return new ModelAndView("/add_product.html");
	}
	
	@GetMapping("/create_quiz1")
	public ModelAndView createQuiz() {
		return new ModelAndView("create_quiz1.html");
	}
}
