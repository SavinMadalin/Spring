package com.example.Spring.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.Spring.controllers.PersonController;
import com.example.Spring.models.Person;

@Component
public class PersonAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>>{

	@Override
	public EntityModel<Person> toModel(Person person) {
		return EntityModel.of(person, 
				linkTo(methodOn(PersonController.class).findPersonsByName(person.getName()) ).withRel("Persons by name"),
				linkTo(methodOn(PersonController.class).findPersonsByAge(person.getAge())).withRel("Persons by age"),
				linkTo(methodOn(PersonController.class).findPersonById(person.getId())).withSelfRel(),
				linkTo(methodOn(PersonController.class).findAllPersons()).withRel("All persons")
				
				
				);
	}
	
	
}
