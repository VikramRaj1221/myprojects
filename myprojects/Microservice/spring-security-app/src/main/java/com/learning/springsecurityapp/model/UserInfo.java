package com.learning.springsecurityapp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;


@Entity
@NamedStoredProcedureQueries({@NamedStoredProcedureQuery(name="add", procedureName="add",
parameters= {@StoredProcedureParameter(mode=ParameterMode.IN, name="a", type=Integer.class),
		@StoredProcedureParameter(mode=ParameterMode.IN, name="b", type=Integer.class) }
)
})
@SqlResultSetMapping(
		name="CustomUserInfo",
		classes=@ConstructorResult(
				targetClass=CustomUserInfo.class,
				columns= {
					@ColumnResult(name="user_Id", type=Long.class),
					@ColumnResult(name="user_Name")
						
				}
			)
		)
@NamedNativeQueries(@NamedNativeQuery(name="customQuery",query="select a.user_Id, a.user_Name from user_info a where a.user_Name=:username",resultSetMapping="CustomUserInfo"))
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
