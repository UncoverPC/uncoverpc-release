package com.uncoverpc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.Roles;
import com.uncoverpc.model.user.User;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	private static final String URI_PATH = "";
	private static final String FOLDER_PATH = "";

	@GetMapping(URI_PATH + "/login")
	public ModelAndView login() {
		Object user = SecurityContextHolder.getContext().getAuthentication().getName();
		if (user != null && !user.equals("anonymousUser")) {// checking if already logged in
			// Checking roles
			String role = userService.findByEmail(user.toString()).getRole();
			if (role.equals(Roles.user)) {
				return new ModelAndView("redirect:/user/dashboard");
			} else if (role.equals(Roles.admin)) {
				return new ModelAndView("redirect:/admin/dashboard");
			}
		}
		return new ModelAndView(FOLDER_PATH + "/login");
	}

	@GetMapping(URI_PATH + "/register")
	public ModelAndView register() {
		return new ModelAndView(FOLDER_PATH + "/signUp");
	}

	@PostMapping(URI_PATH + "/register")
	public ModelAndView registerUser(@RequestBody User user) {
		try {
			// checking if account already created
			System.out.println(user);
			User checkUser = userService.findByEmail(user.getEmail());
			if (checkUser != null) {
				throw new Exception();
			}
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			userService.save(user);
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/login");
			modelAndView.addObject("message", "Register Success!");
			
			return modelAndView;
		} catch (Exception e) {
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/register");
			modelAndView.addObject("message", "Email Already Exist!");
			return modelAndView;
		}
	}

}
