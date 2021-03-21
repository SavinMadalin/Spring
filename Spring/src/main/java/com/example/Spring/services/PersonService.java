package com.example.Spring.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.example.Spring.assemblers.PersonAssembler;
import com.example.Spring.controllers.PersonController;
import com.example.Spring.exceptions.PersonNotFoundException;
import com.example.Spring.models.Person;
import com.example.Spring.models.PersonRequest;
import com.example.Spring.repository.PersonRepository;

@Service
public class PersonService {
		
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PersonAssembler personAssembler;
	
	public CollectionModel<EntityModel<Person>> getAllPersons(){
		List<EntityModel<Person>> entity = personRepository.findAll().stream()
				.map(personAssembler::toModel)
				.collect(Collectors.toList());
		return CollectionModel.of(entity,
				linkTo(methodOn(PersonController.class).findAllPersons()).withSelfRel());
	}
	
	public CollectionModel<EntityModel<Person>> getPersonsByName(String name) {
		List<EntityModel<Person>> entity = personRepository.findPersonByName(name).stream()
				.map(personAssembler::toModel)
				.collect(Collectors.toList());
		return  CollectionModel.of(entity,
				linkTo(methodOn(PersonController.class).findPersonsByName(name)).withSelfRel());
	}
	
	public CollectionModel<EntityModel<Person>> getPersonsByAge(int age){
		List<EntityModel<Person>> entity = personRepository.findPersonByAge(age).stream()
													.map(personAssembler::toModel)
													.collect(Collectors.toList());
		return CollectionModel.of(entity,
				linkTo(methodOn(PersonController.class).findPersonsByAge(age)).withSelfRel());
	}
	
	public EntityModel<Person> getPerson(long id){
		Person person = personRepository.findById(id)
				        .orElseThrow(() -> new PersonNotFoundException(id));
		return personAssembler.toModel(person);
	}
	
	public EntityModel<Person> replacePerson(PersonRequest personRequest, long id){
		Person newPerson= new Person();
		newPerson.setAge(personRequest.getAge());
		newPerson.setEmail(personRequest.getEmail());
		newPerson.setName(personRequest.getName());
		
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
  			return entity;
	}
	
	public String createPerson(PersonRequest personRequest){
		Person person = new Person();
		person.setAge(personRequest.getAge());
		person.setEmail(personRequest.getEmail());
		person.setName(personRequest.getName());
		Person p = personRepository.save(person);
		EntityModel<Person> entity = personAssembler.toModel(p);
		return "Person was saved";
	}
	
	public String deletePerson(long id) {
		personRepository.deleteById(id);
		return "Person with id: " +id + " was deleted";
	}
}
