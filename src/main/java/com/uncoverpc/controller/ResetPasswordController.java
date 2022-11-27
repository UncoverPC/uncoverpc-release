package com.uncoverpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.ResetPassword;
import com.uncoverpc.model.user.User;

public class ResetPasswordController {
	@Autowired
	private UserService userService;
	
	private static final String FOLDER_PATH = "";
	
	@PostMapping("/resetPassword")
	public ModelAndView passwordChange(@RequestBody ResetPassword reset, HttpServletRequest request) {
		System.out.println("changed");
		ModelAndView model = new ModelAndView(FOLDER_PATH + "/login");
		User user = userService.findByEmail(reset.getEmail());
		user.setPassword(reset.getPassword());
		user.setConfirmPassword(reset.getConfirmPassword());
		userService.save(user);
		model.addObject("message", "Password Changed Successfully");
		return model;
	}
}
