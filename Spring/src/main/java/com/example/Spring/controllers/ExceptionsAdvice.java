package com.example.Spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Spring.exceptions.CarNotFoundException;
import com.example.Spring.exceptions.PersonNotFoundException;


@ControllerAdvice
public class ExceptionsAdvice {
	
	@ResponseBody
	@ExceptionHandler(PersonNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String personNotFoundHandler(PersonNotFoundException ex) {
		 return ex.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(CarNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String carNotFoundHandler(CarNotFoundException ex) {
		return ex.getMessage();
	}
	

}
