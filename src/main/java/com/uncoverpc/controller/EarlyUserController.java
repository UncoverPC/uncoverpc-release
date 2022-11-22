package com.uncoverpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.EarlyUserService;
import com.uncoverpc.db.EmailService;
import com.uncoverpc.model.user.EarlyUser;


import net.bytebuddy.utility.RandomString;

public class EarlyUserController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EarlyUserService earlyUserService;
	
	@PostMapping("/register/early")
	public ModelAndView registerEarlyAccess(EarlyUser earlyUser, HttpServletRequest request) {
		try {
			EarlyUser checkUser = earlyUserService.findByEmail(earlyUser.getEmail());
			if (checkUser != null) {
				throw new Exception();
			}

			String randomCode = RandomString.make(64);
			earlyUser.setVerificationCode(randomCode);
			
			earlyUserService.save(earlyUser);
			
			emailService.sendEarlyVerificationEmail(earlyUser, getSiteURL(request));
			
			ModelAndView modelAndView = new ModelAndView("/home");
			modelAndView.addObject("message", "Register Success!");
			return modelAndView;
			
		} catch (Exception e) {
		
		ModelAndView modelAndView = new ModelAndView("/home");
		modelAndView.addObject("message", "Email Already Exists!");
		return modelAndView;
	}
	}

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  
}
