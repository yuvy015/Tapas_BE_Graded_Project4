package com.greatlearning.sems.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.sems.entity.Employee;
import com.greatlearning.sems.repository.EmployeeRepository;
import com.greatlearning.sems.service.EmployeeService;
import org.springframework.data.domain.Sort;

@Service
public class EmployeeServiceImpl implements EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public List<Employee> findAll() {
    List<Employee> employees = employeeRepository.findAll();
    return employees;
  }
  @Override
  public void save(Employee employee) {
      employeeRepository.save(employee);                    
  }
  
  @Override
  public Employee findById(int employeeId) {
      return employeeRepository.findById(employeeId).get();
  }  
  @Override
  public void saveOrUpdate(int id, String firstName, String lastName, String emailId) 
{

    System.out.println("Employee ID ->" + id);

    Employee employeeObj = null;
    if (id == 0) {

      employeeObj = new Employee(firstName, lastName, emailId);
      System.out.println("Add Employee Scenario");
    } else {

      System.out.println("Update Employee Scenario");

      employeeObj = this.findById(id);
      employeeObj.setFirstName(firstName);
      employeeObj.setLastName(lastName);
      employeeObj.setEmailId(emailId);
    }

    // Save/Update the employee
    this.save(employeeObj);
  }
  @Override
  public void deleteById(int id) {
      employeeRepository.deleteById(id);    
  }
  @Override
  public List<Employee> searchBy(String firstName) {
    List<Employee> employees 
      = employeeRepository. findByfirstNameContainsAllIgnoreCase(
        firstName);
    return employees;
  } 
  @Override
  public List<Employee> findAllEmployeesSortedByFirstName(boolean ascending) {
      Sort sort = ascending ? Sort.by("firstName").ascending() : Sort.by("firstName").descending();
      return employeeRepository.findAll(sort);
}
}
  