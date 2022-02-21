package com.blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.demo.dto.RegisterRequest;
import com.blog.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<Long> signup(@RequestBody RegisterRequest request){
		Long newId = authService.signup(request);
		return ResponseEntity.status(HttpStatus.OK).body(newId);
	}
	
}
