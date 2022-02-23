package com.blog.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.demo.dto.LoginRequest;
import com.blog.demo.dto.RegisterRequest;
import com.blog.demo.exception.BadNewUserRequestException;
import com.blog.demo.model.User;
import com.blog.demo.repository.UserRepository;
import com.blog.demo.security.JwtProvider;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
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
		return userRepo.findByUsername(newUser.getUsername())
				.orElseThrow(()->new UsernameNotFoundException(newUser.getUsername() + " not found!!"))
				.getId();
		
	}
	
	public String login(LoginRequest request) {
		// TODO Auto-generated method stub
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getUsername(), request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return jwtProvider.generateToken(authenticate);
	}
	
	private String encodePassword(String password) {
		// TODO Auto-generated method stub
		return encoder.encode(password);
	}

}
