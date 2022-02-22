package com.blog.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BadNewUserRequestAdvice {
	
	@ResponseBody
	@ExceptionHandler(BadNewUserRequestException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	String noSuchTaskHandler(BadNewUserRequestException e) {
		return e.getMessage();
	}
	
}
