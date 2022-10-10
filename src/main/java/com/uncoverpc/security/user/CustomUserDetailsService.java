package com.uncoverpc.security.user;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uncoverpc.db.UserService;
import com.uncoverpc.model.user.User;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		try {
			User user = userService.findByEmail(email);

			if (user == null) {
				throw new UsernameNotFoundException("User not found");
			}
			return new CustomUserDetails(user);
		} catch (Exception e) {
//			e.printStackTrace();
			System.err.println("User not found: " + email);
		}
		throw new UsernameNotFoundException("User not found");
	}
	
	
}
