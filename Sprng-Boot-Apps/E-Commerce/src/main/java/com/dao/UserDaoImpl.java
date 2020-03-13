package com.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.User;
import com.repository.UserRepository;

@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

}
