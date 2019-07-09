package com.learning.jwtsecuritytest.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;


@Entity
public class UserInfo {

	@Id
	@GeneratedValue
	private Long userId;
	
	private String userName;
	private String password;
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role", joinColumns= {@JoinColumn(name="userId")}, inverseJoinColumns= {@JoinColumn(name="roleId")})
	private Set<RoleInfo> roleInfo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<RoleInfo> getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(Set<RoleInfo> roleInfo) {
		this.roleInfo = roleInfo;
	}


	
	
}
