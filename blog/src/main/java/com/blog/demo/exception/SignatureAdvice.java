package com.blog.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class SignatureAdvice {
	
	@ResponseBody
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String userPrincipalNotFoundHandler(SignatureException e) {
		return e.getMessage();
	}

}
