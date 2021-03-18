package com.example.Spring;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="cars")
public class Car {
	 
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min= 2, max=20)
	@Column(name="brands")
	private String brand;
	
	@Min(500)
	@Max(5000)
	@Column(name="engine_capacity")
	private int capacity;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	@JsonIgnoreProperties("car")
	private Person person;
	
	public Car() {}

	public Car(@Size(min = 2, max = 20) String brand, @Min(500) @Max(5000) int capacity) {
		super();
		this.brand = brand;
		this.capacity = capacity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", capacity=" + capacity + "]";
	}
	
	
	
}
