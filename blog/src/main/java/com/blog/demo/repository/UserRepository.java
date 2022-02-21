package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);
	public User findByEmail(String email);

}
