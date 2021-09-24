package com.mission.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

}
