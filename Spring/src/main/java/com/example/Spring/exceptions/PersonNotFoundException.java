package com.example.Spring.exceptions;

public class PersonNotFoundException extends RuntimeException {

	public PersonNotFoundException(long id) {
		super("Could not find any person with id:" + id );
	}
}
