package com.blog.demo.exception;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserPrincipalNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserPrincipalNotFoundException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	String userPrincipalNotFoundHandler(UserPrincipalNotFoundException e) {
		return e.getMessage();
	}

}
