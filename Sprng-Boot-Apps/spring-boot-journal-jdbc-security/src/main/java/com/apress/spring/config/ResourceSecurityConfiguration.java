package com.apress.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalAuthentication
public class ResourceSecurityConfiguration extends WebSecurityConfigurerAdapter	{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Version 1
		/*http.authorizeRequests()	
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").authenticated()
			.and()
			.httpBasic();*/
		
		// Version 2
		/*http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/api/**").authenticated()
			.and()
			.formLogin();*/
		
		// Version 3
		http.authorizeRequests() 
			.antMatchers("/").permitAll() 
			.antMatchers("/api/**").authenticated() 
			.and() 
			.formLogin().loginPage("/login").permitAll() 
			.and()
		   .logout().permitAll();
	}
}
