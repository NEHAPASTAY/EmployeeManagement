package com.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByName(String name);

	Optional<Employee> findByEmail(String email);

	Optional<Employee> findById(int id);

	boolean existsByEmail(String email);
	


	
	
	

	
}
