package com.example.Spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.models.User;
import com.example.Spring.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public User showUser() {
		return userService.getUser();
	}
	
	@GetMapping("/users")
	public User[] showUsers() {
		return userService.getUsers();
	}
}
