package com.learning.springsecurityapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springsecurityapp.model.CustomUserInfo;
import com.learning.springsecurityapp.model.RoleInfo;
import com.learning.springsecurityapp.model.UserInfo;
import com.learning.springsecurityapp.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repo;

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List add(int a, int b) {
		return entityManager.createNamedStoredProcedureQuery("add").setParameter("a", a).setParameter("b", b)
				.getResultList();
	}

	public List<CustomUserInfo> getQuery(String userName) {
		Query query = entityManager.createNamedQuery("customQuery");
		query.setParameter("username", userName);
		List<CustomUserInfo> result = query.getResultList();
		return result;
	}

	public List getDataFromCriteria(String userName) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomUserInfo> query = cb.createQuery(CustomUserInfo.class);
		Root<UserInfo> rootUsr = query.from(UserInfo.class);
		Join<UserInfo, RoleInfo> roleJoin=rootUsr.join("RoleInfo",JoinType.INNER);
		
		//Root<RoleInfo> rootRole = query.from(RoleInfo.class);

		List<Predicate> conditions = new ArrayList<Predicate>();
		conditions.add(cb.equal(rootUsr.get("roleInfo").get("roleId"), roleJoin.get("roleId")));
		conditions.add(cb.equal(rootUsr.get("userName"), userName));

		TypedQuery<CustomUserInfo> finalQuery = entityManager
				.createQuery(query.multiselect(rootUsr.get("userId"), rootUsr.get("userName"), roleJoin.get("roleName"))
						.where(conditions.toArray(new Predicate[] {})).orderBy(cb.desc(rootUsr.get("userName"))));
		return finalQuery.getResultList();
	}

}
