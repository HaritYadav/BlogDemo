package com.blog.demo.security;

import java.security.Key;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
	
	private Key key;
	
	@PostConstruct
	public void init() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	public String generateToken(Authentication authentication) {		
		User user = (User) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(user.getUsername())
				.signWith(key)
				.compact();
	}
	
}
