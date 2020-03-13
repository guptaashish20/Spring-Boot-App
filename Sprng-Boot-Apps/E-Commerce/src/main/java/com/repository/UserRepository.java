package com.repository;

import java.util.List;

import com.domain.User;

public interface UserRepository {
	List<User> findAll();
}
