package com.example.Spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Spring.models.User;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;
	
	public User getUser() {
		String url = "https://jsonplaceholder.typicode.com/posts/1";
		User user = restTemplate.getForObject(url, User.class);
		return user;
		
	}
	
	public User[] getUsers(){
		String url = "https://jsonplaceholder.typicode.com/posts";
		User[] users = restTemplate.getForObject(url, User[].class);
		return users;
	}
}
