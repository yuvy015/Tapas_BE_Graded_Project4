package com.greatlearning.sems.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.greatlearning.sems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	java.util.List<Employee> findByfirstNameContainsAllIgnoreCase(String firstName);
	

}  
