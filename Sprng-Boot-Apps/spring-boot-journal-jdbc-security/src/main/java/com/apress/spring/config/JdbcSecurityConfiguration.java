package com.apress.spring.config;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter	{
	
	@Bean
	public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate)	{
		System.out.println("JdbcSecurityConfiguration.userDetailsService()");
		String query = "SELECT * from  Account where account_name = ?";
		RowMapper<User> userRowMapper = (ResultSet rs, int i) -> 
						new User(rs.getString("ACCOUNT_NAME"), rs.getString("PASSWORD"), rs.getBoolean("ENABLED"),
								rs.getBoolean("ENABLED"), rs.getBoolean("ENABLED"), rs.getBoolean("ENABLED"),
								AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN"));
		
						return username -> jdbcTemplate.queryForObject(query, userRowMapper, username);
	}

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("JdbcSecurityConfiguration.init()");
		try {
			
			auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
			System.out.println("init() end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
