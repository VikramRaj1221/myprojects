package com.learning.jwtsecuritytest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.jwtsecuritytest.model.RoleInfo;

public interface RoleRepository extends JpaRepository<RoleInfo, Long>{

}
