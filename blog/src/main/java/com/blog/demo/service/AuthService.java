package com.blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.RegisterRequest;
import com.blog.demo.exception.BadNewUserRequestException;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	public Long signup(RegisterRequest newUserRequest) throws BadNewUserRequestException {
		
		User newUser = new User();
		newUser.setUsername(newUserRequest.getUsername());
		newUser.setPassword(encodePassword(newUserRequest.getPassword()));
		newUser.setEmail(newUserRequest.getEmail());
		
		try {
			userRepo.save(newUser);
		} catch (Exception e) {
			// TODO: handle exception
			throw new BadNewUserRequestException("Username and/or Email already used!!");
		}
		return userRepo.findByUsername(newUser.getUsername()).getId();
		
	}

	@Autowired
	private PasswordEncoder encoder;
	
	private String encodePassword(String password) {
		// TODO Auto-generated method stub
		return encoder.encode(password);
	}
}
