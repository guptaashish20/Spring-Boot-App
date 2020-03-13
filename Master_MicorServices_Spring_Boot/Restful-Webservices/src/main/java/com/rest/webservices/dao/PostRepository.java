package com.rest.webservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservices.model.Post;
import com.rest.webservices.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
