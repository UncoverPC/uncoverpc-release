package com.uncoverpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.ResetPassword;
import com.uncoverpc.model.user.User;

@Controller
public class ResetPasswordController {
	@Autowired
	private UserService userService;
	
	private static final String FOLDER_PATH = "/users";
	
	@GetMapping("/resetPassword")
	public ModelAndView resetPassword(@Param("code") String code) {
		User user = userService.findByVerificationCode(code);
		ModelAndView model = new ModelAndView(FOLDER_PATH +"/resetPassword.html");
		if(user.getEmail() == null) {
			model.addObject("message","error");
			return model;
		}
		else {
			model.addObject("email", user.getEmail());
			return model;
		}
	}
	
	@PostMapping("/resetPassword")
	public ModelAndView passwordChange( ResetPassword reset, HttpServletRequest request) {
		ModelAndView model = new ModelAndView(FOLDER_PATH + "/login");
		User user = userService.findByEmail(reset.getEmail());
		user.setPassword(reset.getPassword());
		user.setConfirmPassword(reset.getConfirmPassword());
		userService.save(user);
		model.addObject("message", "Password Changed Successfully");
		return model;
	}
}
