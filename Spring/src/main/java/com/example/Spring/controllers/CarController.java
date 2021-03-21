package com.example.Spring.controllers;

import javax.validation.Valid;

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

import com.example.Spring.assemblers.CarAssembler;
import com.example.Spring.models.Car;
import com.example.Spring.models.CarRequest;
import com.example.Spring.repository.CarRepository;
import com.example.Spring.repository.PersonRepository;
import com.example.Spring.services.CarService;
import com.example.Spring.services.CsvService;

@RestController
public class CarController {

		@Autowired
		private CarService carService;
		
		@GetMapping("/cars/all")
		public ResponseEntity<CollectionModel<EntityModel<Car>>> findAllCars() {
			return ResponseEntity.ok(
						carService.getAllCars()
					);
		}
		
		@GetMapping("/cars/brand")
		public ResponseEntity<CollectionModel<EntityModel<Car>>> findCarsByBrand(@RequestParam String brand) {
			return ResponseEntity.ok(
					carService.getCarsByBrand(brand)			
				  	);
		}
		
		@GetMapping("/cars/person/{personId}")
		public ResponseEntity<CollectionModel<EntityModel<Car>>> findCarsByPersonId(@PathVariable long personId){
			return ResponseEntity.ok(
								carService.getCarsByPersonId(personId)
					);
		}
		
		@GetMapping("/car/capacity")
		public ResponseEntity<CollectionModel<EntityModel<Car>>> findCarsByCapacity(@RequestParam int capacity){
			return ResponseEntity.ok(carService.getCarsByCapacity(capacity));
		}
	
		
		@GetMapping("/car/{id}")
		public ResponseEntity<EntityModel<Car>> getCar(@PathVariable long id){
			return ResponseEntity.ok(
					carService.findCar(id)		
					);
		}
		
		@PostMapping("/car/save/{personId}")
		public ResponseEntity<String> saveCar(@RequestBody @Valid CarRequest carRequest,
												@PathVariable long personId){
			
			return ResponseEntity.ok(
					carService.createCar(carRequest, personId)
					);	
		}
		
		@PutMapping("/update/car/{id}/person/{personId}")
		public ResponseEntity<EntityModel<Car>> updateCar(@PathVariable long id, @RequestBody @Valid CarRequest newCar, 
														@PathVariable long personId){

			return ResponseEntity.ok(carService.replaceCar(newCar, id, personId));
		}
		
		@DeleteMapping("/delete/car/{id}")
		public ResponseEntity<String> deleteCar(@PathVariable long id) {
			return ResponseEntity.ok(carService.removeCar(id));
		}
}
