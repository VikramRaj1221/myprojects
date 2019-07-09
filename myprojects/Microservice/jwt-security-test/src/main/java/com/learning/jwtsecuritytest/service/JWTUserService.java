package com.learning.jwtsecuritytest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.jwtsecuritytest.model.JWTUserDetails;
import com.learning.jwtsecuritytest.model.UserInfo;
import com.learning.jwtsecuritytest.repo.UserRepository;

@Service
public class JWTUserService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserInfo user=userRepo.findByUserName(userName);
		if(null==user) throw new UsernameNotFoundException("User Name not found");
		
		return new JWTUserDetails(user.getUserName(), user.getPassword(), user.getRoleInfo());
	}


}
