package com.jsp.ums.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.ums.exceptions.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationExceptionHandler {
	
	@Autowired
	private ErrorStrucrtureClass<String>structure;
	@ExceptionHandler
	public ResponseEntity<ErrorStrucrtureClass<String>>handleUserNotFoundByIdException(UserNotFoundByIdException userEx){
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setMessage(userEx.getMessage());
		structure.setRootCause("The Requested User With The Given ID Is Not Found!!");
		
		return new ResponseEntity<ErrorStrucrtureClass<String>>(structure,HttpStatus.NOT_FOUND);
		
	}
	

}
