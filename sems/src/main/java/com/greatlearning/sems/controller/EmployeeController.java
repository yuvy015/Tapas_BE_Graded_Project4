package com.greatlearning.sems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.sems.entity.Employee;
import com.greatlearning.sems.service.EmployeeService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;
import java.security.Principal;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @RequestMapping("/list")
    public String listEmployees(Model theModel) {
        
        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);                

        return "list-employees";
    }
    @RequestMapping("/displayEmployeeForm")
    public String addEmployee_Step1(Model theModel) {

        Employee employee = new Employee();

        theModel.addAttribute("employee", employee);

        return "employee-form";
    } 
    @PostMapping("/save")
    public String saveEmployee(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName, @RequestParam("emailId") String emailId) {

      employeeService.saveOrUpdate(id, firstName, lastName, emailId);
      
      // use a redirect to 'employees-listing'
      return "redirect:/employees/list";
    }  
    @RequestMapping("/displayEmployeeForm_Update")
    public String updateEmployee_Step1(
        @RequestParam("employeeId") int employeeId,
            Model theModel) {

        Employee employee = employeeService.findById(employeeId);

        // set Employee as a model attribute to pre-populate the form
        theModel.addAttribute("employee", employee);

        // send over to our form
        return "employee-form";   
    }
    @RequestMapping("/delete")
    public String delete(@RequestParam("employeeId") int employeeId) {

        // delete the Employee
        employeeService.deleteById(employeeId);

        // redirect to 'employees-listing'
        return "redirect:/employees/list";
    }
    @RequestMapping("/search")
    public String search(@RequestParam("firstName") String firstName, Model theModel) {

      // Check the name and the author names
      // If both of them are empty, then just give list of all Employees
      if (firstName.trim().isEmpty()) {
        return "redirect:/employees/list";
      } else {

        // Else, search by 'employee name'
        List<Employee> employees = employeeService.searchBy(firstName);

        // Add it to the UI Model
        theModel.addAttribute("employees", employees);

        // Returns the 'employees-listing' page
        return "list-employees";
      }
     }   
    
    @GetMapping("/sort")
    public String sortEmployees(@RequestParam("order") String order, Model model) {
        boolean ascending = order.equalsIgnoreCase("asc");
        List<Employee> sortedEmployees = employeeService.findAllEmployeesSortedByFirstName(ascending);
        model.addAttribute("employees", sortedEmployees);
        return "list-employees";  // This should be the name of your view template
    }
    @RequestMapping(value = "/403")
    public ModelAndView handleAccessDeniedError(Principal user) {

      ModelAndView model = new ModelAndView();

      if (user != null) {
        model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
      } else {
        model.addObject("msg", "You do not have permission to access this page!");
      }

      model.setViewName("authorization-error-403");
      return model;
    }    
} 