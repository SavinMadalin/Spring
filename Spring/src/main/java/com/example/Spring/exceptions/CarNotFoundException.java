package com.example.Spring.exceptions;

public class CarNotFoundException extends RuntimeException{
	
	public CarNotFoundException(long id) {
		super("Could not find any car with id: " +id );
	}

}
