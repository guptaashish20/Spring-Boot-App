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

import com.rest.webservices.dao.PostRepository;
import com.rest.webservices.dao.UserDaoService;
import com.rest.webservices.dao.UserRepository;
import com.rest.webservices.exception.UserNotFoundException;
import com.rest.webservices.model.Post;
import com.rest.webservices.model.User;

@RestController
public class UserJPAResource {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("id-"+id);
		
		// "all-user", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<User> model = new EntityModel<>(user.get());
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		
		return model;
		// return user;
	}
	
	// input - details of user
	// output - CREATED & Return the created URI
	
	// HATEOAS ( Hypermedia As The Engine Of Application States
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)	{
		System.out.println("UserJPAResource.createUser()");
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)	{
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrieveAllUser(@PathVariable int id)	{
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id-" + id);
		
		return userOptional.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id,@RequestBody Post post)	{
		Optional<User> userOptional = userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("id-" + id);
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
}
