package com.mock5_Q2.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExcpetionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException e, WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(e.getMessage());
		err.setTime(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmailException.class)
	public ResponseEntity<MyErrorDetails> EmailExceptionHandler(EmailException e, WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(e.getMessage());
		err.setTime(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> ExceptionHandler(Exception e, WebRequest req){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setMessage(e.getMessage());
		err.setTime(LocalDateTime.now());
		err.setDescription(req.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
}
