package com.learning.jwtsecuritytest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.learning.jwtsecuritytest.model.UserInfo;

@Transactional
public interface UserRepository extends JpaRepository<UserInfo, Long>{

	UserInfo findByUserName(String userName) throws UsernameNotFoundException;
	
}
