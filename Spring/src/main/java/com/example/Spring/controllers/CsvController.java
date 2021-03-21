package com.example.Spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.models.Person;
import com.example.Spring.services.CsvService;

@RestController
public class CsvController {
	
	@Autowired
	private CsvService csvService;
	
	@PostMapping("/csv")
	public String showCsv() {
		return csvService.getCsvContent();
	}
}
