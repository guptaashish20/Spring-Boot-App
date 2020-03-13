package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dal.UserDAL;
import com.dal.UserRepository;
import com.model.User;

@RestController
@RequestMapping(value = "/")
public class UserController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private final UserRepository userRepository;
	private final UserDAL userDAL;

	public UserController(UserRepository userRepository, UserDAL userDal) {
		this.userRepository = userRepository;
		this.userDAL = userDal;
	}

	/* http://localhost:8102 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<User> getAllUsers() {
		log.info("getAllUsers");
		return userRepository.findAll();
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) {
		log.info("Getting user with ID: {}.", userId);
		return userRepository.findById(userId).orElse(null);
	}

	/*
	 * http://localhost:8102/create 
	 * 
	 * { "name" : "Shubham2", "userSettings" : { "bike": "pulsar2" } }
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public User addNewUsers(@RequestBody User user) {
		log.info("Saving user.");
		return userRepository.save(user);
	}

	@RequestMapping(value = "/settings/{userId}", method = RequestMethod.GET)
	public Object getAllUserSettings(@PathVariable String userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			return userDAL.getAllUserSettings(userId);
		} else {
			return "User not found.";
		}
	}

	@RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
	public String getUserSetting(@PathVariable String userId, @PathVariable String key) {
		return userDAL.getUserSetting(userId, key);
	}

	@RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
	public String addUserSetting(@PathVariable String userId, @PathVariable String key, @PathVariable String value) {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			user.getUserSettings().put(key, value);
			userRepository.save(user);
			return "Key added";
		} else {
			return "User not found.";
		}
	}
}
