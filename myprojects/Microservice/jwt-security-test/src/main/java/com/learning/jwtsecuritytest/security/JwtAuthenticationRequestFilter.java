package com.learning.jwtsecuritytest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.learning.jwtsecuritytest.service.JWTUserService;
import com.learning.jwtsecuritytest.util.JwtTokenUtil;

@Component
public class JwtAuthenticationRequestFilter extends OncePerRequestFilter{

	@Value("${jwt.authorization.header}")
	private String hearName;
	
	@Autowired
	JwtTokenUtil jwtUtil;
	
	@Autowired
	JWTUserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chainFilter)
			throws ServletException, IOException {
		String userName=null;
		UserDetails userDetails=null;
		System.out.println("Coming here "+hearName);
		System.out.println(request.getHeader(hearName));
		if(null!=request.getHeader(hearName)) {
			String token=request.getHeader(hearName).substring(7);
			
			if(jwtUtil.IstokenValid(token)) {
				System.out.println("Token is valid");
				userName=jwtUtil.getUserName(token);
				userDetails=userService.loadUserByUsername(userName);	
			}else {
				System.out.println("Invalid");
			}
			
			
			if(null!=userName &&  userDetails.getUsername().equals(userName)) {
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		System.out.println("Coming to last");
		chainFilter.doFilter(request, response);
		
	}

	
}
