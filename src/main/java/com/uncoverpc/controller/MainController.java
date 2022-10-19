package com.uncoverpc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

	@GetMapping("/home")
	public String home() {
		return "index.html";
	}

	@GetMapping("/admin/Dashboard")
	@ResponseBody
	public String adminDashbaord() {
		return "under development";
	}
	
	@GetMapping("/explore")
	public String explorePage() {
		return "explore.html";
	}
}
