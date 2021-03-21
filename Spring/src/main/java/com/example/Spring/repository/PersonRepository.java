package com.example.Spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findPersonByName(String name);
	List<Person> findPersonByAge(int age);
	
}
