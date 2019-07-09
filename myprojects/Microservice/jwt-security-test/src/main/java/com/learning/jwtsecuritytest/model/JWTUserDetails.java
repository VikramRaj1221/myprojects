package com.learning.jwtsecuritytest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTUserDetails implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private List<SimpleGrantedAuthority> grantedList;
	
	public JWTUserDetails(String userName,String password, Set<RoleInfo> role) {
		this.userName=userName;
		this.password=password;
		List<SimpleGrantedAuthority> grantedList=new ArrayList<>();
		grantedList=role.stream().map(r->new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
		
		this.grantedList=grantedList;
	}
	
	
	
	public String getUserName() {
		return userName;
	}



	public List<SimpleGrantedAuthority> getGrantedList() {
		return grantedList;
	}



	public void setGrantedList(List<SimpleGrantedAuthority> grantedList) {
		this.grantedList = grantedList;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
