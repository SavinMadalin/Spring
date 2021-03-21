package com.example.Spring.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="persons")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "person_generator")
	@SequenceGenerator(name="person_generator", sequenceName = "person_generator", allocationSize = 1)
	private long id;
	
	@Size(min=2, max=30)
	@Column(name="person_name")
	private String name;
	
	@Min(18)
	@Column(name="person_age")
	private int age;
	
	@Email
	@Column(name="person_email")
	private String email;
	
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnoreProperties("person")
	private List<Car> car;
	
	public Person() {}

	public Person(@Size(min = 2, max = 30) String name, @Min(18) int age, @Email String email) {
		this.name = name;
		this.age = age;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", car=" + car + "]";
	}
	
	
}
