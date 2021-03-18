package com.example.Spring;

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

@RestController
public class PersonController {

		@Autowired
		private PersonRepository personRepository;
		
		@Autowired
		private PersonAssembler personAssembler;
		
		@GetMapping("/persons/all")
		public ResponseEntity<?> findAllPersons() {
			
			List<EntityModel<Person>> entity = personRepository.findAll().stream()
												.map(personAssembler::toModel)
												.collect(Collectors.toList());
			
			return ResponseEntity.ok(
						CollectionModel.of(entity,
								linkTo(methodOn(PersonController.class).findAllPersons()).withSelfRel())
						);
			
		}
		
		@GetMapping("/persons/name")
		public ResponseEntity<?> findPersonsByName(@RequestParam String name) {

			List<EntityModel<Person>> entity = personRepository.findPersonByName(name).stream()
																.map(personAssembler::toModel)
																.collect(Collectors.toList());
																
																
			
			return ResponseEntity.ok(
						CollectionModel.of(entity,
								linkTo(methodOn(PersonController.class).findPersonsByName(name)).withSelfRel())
						);
			
		}
		
		@GetMapping("/persons/age")
		public ResponseEntity<?> findPersonsByAge(@RequestParam int age) {

			List<EntityModel<Person>> entity = personRepository.findPersonByAge(age).stream().map(personAssembler::toModel).collect(Collectors.toList());
			
			return ResponseEntity.ok(
						CollectionModel.of(entity,
								linkTo(methodOn(PersonController.class).findPersonsByAge(age)).withSelfRel())
						);
		}
		
		@GetMapping("/person/id/{id}")
		public ResponseEntity<?> findPersonById(@PathVariable long id) {
	
			EntityModel<Person> entity = personAssembler.toModel(personRepository.findById(id).get());
																	
			return ResponseEntity.ok(entity);
			
		}
		
		@PutMapping("/person/update/{id}")
		public ResponseEntity<?> updatePerson(@RequestBody @Valid Person newPerson, @PathVariable long id) {
			 Person p= personRepository.findById(id)
								.map(person->{
									person.setName(newPerson.getName());
									person.setAge(newPerson.getAge());
									person.setEmail(newPerson.getEmail());
									return personRepository.save(person);
								})
								.orElseGet(() -> {
									newPerson.setId(id);
									return personRepository.save(newPerson);
								});
			 EntityModel<Person> entity = personAssembler.toModel(p);
			return ResponseEntity.ok(entity);
			
		}
		
		@PostMapping("/person/save")
		public ResponseEntity<?> savePerson(@RequestBody @Valid Person person) {
			Person p = personRepository.save(person);
			EntityModel<Person> entity = personAssembler.toModel(p);
			return ResponseEntity.ok(entity);
		}
		
		@DeleteMapping("/person/delete/{id}")
		public void deletePerson(@PathVariable long id) {
			 personRepository.deleteById(id);
		}
}
