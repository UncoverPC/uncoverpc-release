package com.uncoverpc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrController implements ErrorController{

	private static final String PATH = "errors/";
	
	@GetMapping("/error")
	@ResponseBody
	public ModelAndView handleError(HttpServletRequest request) {
		
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		  if (status != null) {
		        Integer statusCode = Integer.valueOf(status.toString());
		    
		        if(statusCode == HttpStatus.NOT_FOUND.value()) {
		        	return new ModelAndView(PATH + "404.html");
		        }
		        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
		        	
		        }else if(statusCode == HttpStatus.FORBIDDEN.value()) {
		        	return new ModelAndView(PATH+"forbidden.html");
		        }
		        return new ModelAndView(PATH+"error.html").addObject("statusCode", statusCode);
		    }
		  return new ModelAndView(PATH+"error.html").addObject("statusCode", null);
	}
	
}
