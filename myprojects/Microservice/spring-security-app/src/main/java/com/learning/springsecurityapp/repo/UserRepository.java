package com.learning.springsecurityapp.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.learning.springsecurityapp.model.UserInfo;

@Transactional
public interface UserRepository extends JpaRepository<UserInfo, Long>{

	UserInfo findByUserName(String userName) throws UsernameNotFoundException;
	
	@Query(value="select * from User_Info u order by u.user_Name",nativeQuery=true)
	Collection<UserInfo> findbyAll();
	
	@Query(value="select u from UserInfo u where u.userName=:userName ")
	UserInfo findbyname(@Param("userName") String userName);
	

	@Modifying
	@Query("update UserInfo u set u.userName=:newUserName where u.userId=:userId")
	int updateName(@Param("newUserName") String newUserName, 
			@Param("userId") Long userId);
	
}
