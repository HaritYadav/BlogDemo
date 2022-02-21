package com.blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.RegisterRequest;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	public Long signup(RegisterRequest newUserRequest) {
		
		User newUser = new User();
		newUser.setUsername(newUserRequest.getUsername());
		newUser.setPassword(newUserRequest.getPassword());
		newUser.setEmail(newUserRequest.getEmail());
		
		userRepo.save(newUser);
		
		return userRepo.findByUsername(newUser.getUsername()).getId();
		
	}
}
