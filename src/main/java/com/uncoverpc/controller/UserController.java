package com.uncoverpc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.uncoverpc.model.queryResult.queryResult;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.EmailService;
import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.Roles;
import com.uncoverpc.model.user.Roles.Role;
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
			Set<Role> role = userService.findByEmail(user.toString()).getRoles();
			if (role.contains(Roles.Role.USER)) {
				return new ModelAndView("redirect:/user/dashboard");
			} else if (role.contains(Roles.Role.ADMIN)) {
				return new ModelAndView("redirect:/admin/dashboard");
			}
		}
		return new ModelAndView(FOLDER_PATH + "/login");
	}

	@GetMapping(URI_PATH + "/login/oauth2/code/google")
	public ModelAndView OAuth2Login(OAuth2AuthenticationToken token) {
		System.out.println(token);
		return new ModelAndView(FOLDER_PATH+"/login");
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
			System.out.println(user);
			User checkUser = userService.findByEmail(user.getEmail());
			if (checkUser != null) {
	            ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/signUp");
	            modelAndView.addObject("message", "Email Already Exist!");
	            return modelAndView;
			}
			//Adding USER role
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(Roles.Role.USER);
			user.setRoles(roles);
			
			//Deleting confirmpassword
			user.setConfirmPassword(null);

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			String randomCode = RandomString.make(64);
			user.setVerificationCode(randomCode);
			user.setEnabled(false);
			
			userService.save(user);
			
			emailService.sendVerificationEmail(user, getSiteURL(request));
			
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/registersuccess");
			//modelAndView.addObject("message", "Register Success!");
			
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			ModelAndView modelAndView = new ModelAndView(FOLDER_PATH + "/signUp");
			modelAndView.addObject("message", "An unexpected error has occured.");
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

	@GetMapping(URI_PATH + "/registersuccess")
	public ModelAndView registerSuccess() {
		return new ModelAndView(FOLDER_PATH + "/registerSuccess");
	}

	@GetMapping(URI_PATH + "/queryResults")
	public ModelAndView queryResults() throws IOException {
		ModelAndView mav = new ModelAndView(FOLDER_PATH + "queryResults");
		try {
			String path = new File("").getAbsolutePath();
			BufferedReader br = new BufferedReader(new FileReader(path+"/src/main/java/com/uncoverpc/controller/productTest.json"));
			queryResult[] qr = new Gson().fromJson(br, queryResult[].class);
			mav.addObject("products", qr);
		}catch (Exception e){
			System.out.println(e);
		}
		return mav;
	}
}
