package com.rest.webservices.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.dao.UserDaoService;
import com.rest.webservices.exception.UserNotFoundException;
import com.rest.webservices.model.User;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService service;

	// GET /users
	// retrieve all Users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		System.out.println("UserResource.retrieveUser()");
		User user = service.findOne(id);
		if(user == null)
			throw new UserNotFoundException("id-"+id);
		
		// "all-user", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
		// return user;
	}
	
	// input - details of user
	// output - CREATED & Return the created URI
	
	// HATEOAS ( Hypermedia As The Engine Of Application States
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)	{
		User savedUser = service.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)	{
		User user = service.deleteById(id);
		
		if(user == null)	{
			throw new UserNotFoundException("id-"+ id);
		}
	}
	
	
}
