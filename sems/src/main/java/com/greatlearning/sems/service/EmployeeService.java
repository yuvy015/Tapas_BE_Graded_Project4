package com.greatlearning.sems.service;

import java.util.List;
import com.greatlearning.sems.entity.Employee;

public interface EmployeeService {

  public List<Employee> findAll();
  
  public void save(Employee employee);
  
  public Employee findById(int id);
  
  public void saveOrUpdate(int id, String firstName, String lastName, String emailId);
  
  public void deleteById(int id);
  
  public List<Employee> searchBy(String firstName);
  
  List<Employee> findAllEmployeesSortedByFirstName(boolean ascending);

}
