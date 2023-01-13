package com.uncoverpc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uncoverpc.db.EarlyUserService;
import com.uncoverpc.db.EmailService;
import com.uncoverpc.model.user.EarlyUser;

import net.bytebuddy.utility.RandomString;

@Controller
public class EarlyUserController {
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private EarlyUserService earlyUserService;
	
	
	@PostMapping("/earlyaccess")
	@ResponseBody
	public String registerEarlyAccess(@RequestBody EarlyUser earlyUser, HttpServletRequest request) {
		try {
			EarlyUser checkUser = earlyUserService.findByEmail(earlyUser.getEmail());
			if (checkUser != null) {
				throw new Exception();
			}

			String randomCode = RandomString.make(64);
			earlyUser.setVerificationCode(randomCode);
			
			earlyUserService.save(earlyUser);
			
			emailService.sendEarlyVerificationEmail(earlyUser, getSiteURL(request));
			
			return "Register Success!";
			
		} catch (Exception e) {
		return "Failed";
	}
}
	@GetMapping("/verifyEarly")
	public ModelAndView verifyUser(@Param("code") String code) {
		System.out.println("verifying");
	    if (verifyEarly(code)) {
	    	ModelAndView model = new ModelAndView("/users/verifySuccess.html");
	    	return model;
	    } else {
	    	ModelAndView model = new ModelAndView("/users/verifyFail.html");
	    	return model;
	    }
	}
    
    public boolean verifyEarly(String verificationCode) {
        EarlyUser user = earlyUserService.findByVerificationCode(verificationCode);
         
        if (user == null || user.getEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            earlyUserService.save(user);
             
            return true;
        }
         
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }  
}
