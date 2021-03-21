package com.example.Spring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spring.assemblers.PersonAssembler;
import com.example.Spring.models.Person;
import com.example.Spring.models.PersonRequest;
import com.example.Spring.repository.PersonRepository;
import com.example.Spring.services.CsvService;
import com.example.Spring.services.PersonService;

@RestController
public class PersonController {
		
		@Autowired
		private PersonService personService;
		
		
		@GetMapping("/persons/all")
		public ResponseEntity<CollectionModel<EntityModel<Person>>> findAllPersons() {
			return ResponseEntity.ok(
						personService.getAllPersons()
						);
			
		}
		
		@GetMapping("/persons/name")
		public ResponseEntity<CollectionModel<EntityModel<Person>>> findPersonsByName(@RequestParam String name) {
			return ResponseEntity.ok(
						personService.getPersonsByName(name)
						);
			
		}
		
		@GetMapping("/persons/age")
		public ResponseEntity<CollectionModel<EntityModel<Person>>> findPersonsByAge(@RequestParam int age) {
			return ResponseEntity.ok(
						personService.getPersonsByAge(age)
						);
		}
		
		@GetMapping("/person/id/{id}")
		public ResponseEntity<EntityModel<Person>> findPersonById(@PathVariable long id) {													
			return ResponseEntity.ok(personService.getPerson(id));
			
		}
		
		@PutMapping("/person/update/{id}")
		public ResponseEntity<EntityModel<Person>> updatePerson(@RequestBody @Valid PersonRequest newPerson, @PathVariable long id) {
			return ResponseEntity.ok(personService.replacePerson(newPerson, id));
			
		}
		
		@PostMapping("/person/save")
		public ResponseEntity<String> savePerson(@RequestBody @Valid PersonRequest person) {
			return ResponseEntity.ok(personService.createPerson(person));
		}
		
		@DeleteMapping("/person/delete/{id}")
		public ResponseEntity<String> deletePerson(@PathVariable long id) {
			return ResponseEntity.ok(personService.deletePerson(id));
		}
		
}
