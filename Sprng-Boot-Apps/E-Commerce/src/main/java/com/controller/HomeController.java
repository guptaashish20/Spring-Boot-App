package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDao;
import com.domain.User;

@RestController
public class HomeController {

	@Autowired
	UserDao userDao;

	@RequestMapping("/")
	public List<User> demo() {
		//System.out.println("demo()");
		System.out.println("HomeController.demo()");
		return null;
	}
	
	@RequestMapping("/home/getuser")
	public  List<User> home() {
		System.out.println("home()");
		System.out.println(userDao.getUsers());
		return userDao.getUsers();
	}
}
