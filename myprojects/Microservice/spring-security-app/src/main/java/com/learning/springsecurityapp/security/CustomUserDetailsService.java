package com.learning.springsecurityapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.springsecurityapp.model.UserInfo;
import com.learning.springsecurityapp.repo.UserRepository;

@Service
class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo user=repo.findByUserName(userName);
		
		if(null==user)throw new UsernameNotFoundException("User Name not found");
		
		return new CustomUserDetails(user);
	}

}
