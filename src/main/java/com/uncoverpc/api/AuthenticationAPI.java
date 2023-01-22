package com.uncoverpc.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uncoverpc.jwt.AuthenticationResponse;
import com.uncoverpc.jwt.JwtUtil;
import com.uncoverpc.model.user.User;
import com.uncoverpc.model.utils.Utils;
import com.uncoverpc.security.user.CustomUserDetailsService;

@RestController
@RequestMapping("auth/api")
public class AuthenticationAPI {

	// Login or user
	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	protected CustomUserDetailsService userDetailsService;
	
	@Autowired
	protected JwtUtil jwtTokenUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(HttpServletRequest request, @RequestBody User user) throws Exception {
		System.out.println(user.getEmail() + "\n" + user.getPassword());
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(403).build();
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

		final String jwt = Utils.encrypt(jwtTokenUtil.generateToken(userDetails), Utils.SECRET_KEY);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
