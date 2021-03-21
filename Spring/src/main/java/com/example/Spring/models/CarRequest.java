package com.example.Spring.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CarRequest {
	 
		private long id;
		
		@Size(min= 2, max=20)
		private String brand;
		
		@Min(500)
		@Max(5000)
		private int capacity;
		
		private Person person;
		
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

