package com.uncoverpc.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uncoverpc.model.utils.Utils;
import com.uncoverpc.security.user.CustomUserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {//TODO separate into smaller methods
		
			//auth
			final String auth = request.getHeader("Authorization");
			if (auth != null && auth.startsWith("Bearer ")) {
				final String authorizationHeader = Utils.decrypt(auth, "secret");

				String username = null;
				String jwt = null;
				if (authorizationHeader != null) {
					try {
						jwt = authorizationHeader.substring(7);// getting jwt
						username = jwtUtil.extractUsername(jwt);
					} catch (Exception e) {
						response.sendError(403, e.getLocalizedMessage());
						return;
					}
				}

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
					if (jwtUtil.validateToken(jwt, userDetails)) {
						UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						usernamePasswordAuthenticationToken
								.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					}
				}
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			response.sendError(500, e.getLocalizedMessage());
		}

	}

}
