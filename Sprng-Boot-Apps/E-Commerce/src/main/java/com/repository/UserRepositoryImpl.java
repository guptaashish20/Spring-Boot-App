package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.domain.User;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> findAll() {
		return jdbcTemplate.query("select * from users",
				(rs, rowNum) -> new User(rs.getString("username"),rs.getString("password")
		        )
		 );
	}

}
