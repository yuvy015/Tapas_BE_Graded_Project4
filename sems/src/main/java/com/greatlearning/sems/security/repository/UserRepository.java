package com.greatlearning.sems.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greatlearning.sems.security.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username = ?1")
  public User getUserByUsername(String username);

}        
