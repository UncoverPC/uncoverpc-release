package com.uncoverpc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("login")
	public String login() {
		return "login.html";
	}
	
	@GetMapping("register")
	public String register() {
		return "quizNotFound.html";//Placeholder
	}
	
	
	
}
