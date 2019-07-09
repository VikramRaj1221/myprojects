package com.learning.springsecurityapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.springsecurityapp.model.RoleInfo;

public interface RoleRepository extends JpaRepository<RoleInfo, Long>{

}
