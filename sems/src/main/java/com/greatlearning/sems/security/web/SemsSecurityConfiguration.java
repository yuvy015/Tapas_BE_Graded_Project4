package com.greatlearning.sems.security.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatlearning.sems.security.service.impl.SemsUserDetailsServiceImpl;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;    

@Configuration
public class SemsSecurityConfiguration {

  @Bean
  public UserDetailsService userDetailsService() {
    return new SemsUserDetailsServiceImpl();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider semsDaoAuthenticationProvider() {

    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .requestMatchers("/","/employees/list", "/employees/search", "/employees/displayEmployeeForm", "/employees/save").hasAnyAuthority("NORMAL_USER","ADMIN_USER")
      .requestMatchers("/employees/displayEmployeeForm_Update","/employees/delete").hasAuthority("ADMIN_USER")
      .anyRequest().authenticated()
      .and()
      .formLogin().loginProcessingUrl("/login").successForwardUrl("/employees/list").permitAll()
      .and()
      .exceptionHandling().accessDeniedPage("/employees/403")
      .and()
      .cors().and().csrf().disable();
    
    http.authenticationProvider(semsDaoAuthenticationProvider());
    return http.build();
  }   
}  
