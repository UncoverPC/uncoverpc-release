package com.uncoverpc.security.exception.authentication;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void onAuthenticationFailure(
    HttpServletRequest request,
    HttpServletResponse response,
    AuthenticationException exception) 
    throws IOException, ServletException {
      
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
      Map<String, Object> data = new HashMap<>();
      data.put(
        "timestamp", 
        Calendar.getInstance().getTime());
      data.put(
        "exception", 
        exception.getMessage());
      response.getOutputStream()
        .println(objectMapper.writeValueAsString(data));
      if(exception.getMessage().equals("Bad credentials")) {
          response.sendRedirect("/login?error=badCredentials");
      }
      else if(exception.getMessage().equals("User is disabled")) {
          response.sendRedirect("/login?error=unverified");
      }else {
          //TO DO, send email to devs
          //TO DO, add error value 
//          response.send
          response.sendRedirect("/error");
      }
  }

}
