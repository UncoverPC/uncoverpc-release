package com.uncoverpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping("/")
	public ModelAndView homepage() {
		return new ModelAndView("/main/homepage.html");
	}

	@GetMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("/main/homepage.html");
	}
	
	@GetMapping("test")
	public ModelAndView home2() {
	    return new ModelAndView("/main/homepagev2.html");
	}

	@GetMapping("/admin/dashboard")
	public ModelAndView adminDashbaord() {
		return new ModelAndView("/admin/admin_dashboard.html");
	}

	@GetMapping("/explore")
	public ModelAndView explorePage() {
		return new ModelAndView("/main/explore.html");
	}

}
