package com.greatlearning.sems.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "First_Name")
  private String firstName;

  @Column(name = "Last_Name")
  private String lastName;

  @Column(name = "Email_Id")
  private String emailId;

  public Employee() {
  }

  public Employee(String firstName, String lastName, String emailId) 
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailId = emailId;
  }
}          
