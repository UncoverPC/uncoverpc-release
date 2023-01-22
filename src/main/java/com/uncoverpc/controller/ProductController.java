package com.uncoverpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ProductController {
	private static final String ADMIN_PATH = "/admin/product";
	@GetMapping("/add_product")
	public ModelAndView addProduct() {
		return new ModelAndView(ADMIN_PATH+"/add_product.html");
	}
	
}
