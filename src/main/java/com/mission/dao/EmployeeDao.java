package com.mission.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mission.domain.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String>  {

	Optional<Employee>findByUsername(String username);
}

