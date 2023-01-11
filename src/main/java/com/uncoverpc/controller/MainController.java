package com.uncoverpc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {

	@GetMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("homepage.html");
	}

	@GetMapping("/admin/dashboard")
	public ModelAndView adminDashbaord() {
		return new ModelAndView("/admin_dashboard.html");
	}
	
	@GetMapping("/explore")
	public ModelAndView explorePage() {
		return new ModelAndView("explore.html");
	}

	
}
