package com.uncoverpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.EmailService;
import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.Role;
import com.uncoverpc.model.user.User;

import net.bytebuddy.utility.RandomString;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	private static final String URI_PATH = "";
	private static final String FOLDER_PATH = "";
	
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  

	@GetMapping(URI_PATH + "/login")
	public ModelAndView login() {
		Object user = SecurityContextHolder.getContext().getAuthentication().getName();
		if (user != null && !user.equals("anonymousUser")) {// checking if already logged in
			// Checking roles
			String role = userService.findByEmail(user.toString()).getRole();
			if (role.equals(Role.USER)) {
				return new ModelAndView("redirect:/user/dashboard");
			} else if (role.equals(Role.ADMIN)) {
				return new ModelAndView("redirect:/admin/dashboard");
			}
		}
		return new ModelAndView(FOLDER_PATH + "/login");
	}

	@GetMapping(URI_PATH + "/forgotPassword")
	public ModelAndView forgotPassword() {
		return new ModelAndView(FOLDER_PATH + "/forgotPassword");
	}
	
	@GetMapping(URI_PATH + "/register")
	public ModelAndView register() {
		return new ModelAndView(FOLDER_PATH + "/signUp");
	}

	@PostMapping(URI_PATH + "/register")
	public ModelAndView registerUser( User user, HttpServletRequest request) {
		//checking if passwords are the same
		try {
			if(!(user.getPassword().equals(user.getConfirmPassword()))) {
				throw new Exception();
			}
		}
		catch (Exception e){
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/signUp");
			modelAndView.addObject("message", "Passwords don't match!");
			return modelAndView;
		}
		
		try {
			// checking if account already created
			//TO DO check if user contains a email and password
			System.out.println(user);
			User checkUser = userService.findByEmail(user.getEmail());
			if (checkUser != null) {
				throw new Exception();
			}

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			user.setEnabled(false);
			
			userService.save(user);
			
			emailService.sendVerificationEmail(user, getSiteURL(request));
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/login");
			modelAndView.addObject("message", "Register Success!");
			
			return modelAndView;
		} catch (Exception e) {
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/signUp");
			modelAndView.addObject("message", "Email Already Exist!");
			return modelAndView;
		}
	}
	
	@PostMapping(URI_PATH+"/register/early") 
	public ModelAndView registerEarly(User user, HttpServletRequest request){
		try {
			// checking if account already created
			//TO DO check if user contains a email and password
			System.out.println(user);
			User checkUser = userService.findByEmail(user.getEmail());
			if (checkUser != null) {
				throw new Exception();
			}

			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			user.setEnabled(false);
			
			userService.save(user);
			
			emailService.sendVerificationEmail(user, getSiteURL(request));
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/login");
			modelAndView.addObject("message", "Register Success!");
			
			return modelAndView;
		} catch (Exception e) {
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/signUp");
			modelAndView.addObject("message", "Email Already Exist!");
			return modelAndView;
		}
		
	}
	
	@GetMapping(URI_PATH + "/verify")
	public String verifyUser(@Param("code") String code) {
	    if (emailService.verify(code)) {
	        return "verifySuccess.html";
	    } else {
	        return "verifyFail.html";
	    }
	}

}
