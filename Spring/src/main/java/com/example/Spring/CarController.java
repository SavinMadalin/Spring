package com.example.Spring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

@RestController
public class CarController {

		@Autowired
		private CarRepository carRepository;
		
		@Autowired
		private CarAssembler carAssembler;
		
		@Autowired
		private PersonRepository personRepository;
		
		@GetMapping("/cars/all")
		public ResponseEntity<?> findAllCars() {
			List<EntityModel<Car>> list = carRepository.findAll().stream()
											.map(carAssembler::toModel)
											.collect(Collectors.toList());
			
			return ResponseEntity.ok(
						CollectionModel.of(list, 
								linkTo(methodOn(CarController.class).findAllCars()).withSelfRel()
								)
					);
		}
		
		@GetMapping("/cars/brand")
		public ResponseEntity<?> findCarsByBrand(@RequestParam String brand) {
			List<EntityModel<Car>> list = carRepository.findCarByBrand(brand).stream()
					.map(carAssembler::toModel)
					.collect(Collectors.toList());

			return ResponseEntity.ok(
								CollectionModel.of(list, 
									linkTo(methodOn(CarController.class).findCarsByBrand(brand)).withSelfRel()
										)
					);
		}
		
		@GetMapping("/cars/person/{personId}")
		public ResponseEntity<?> findCarsByPersonId(@PathVariable long personId){
			List<EntityModel<Car>> list = carRepository.findCarByPersonId(personId).stream()
					.map(carAssembler::toModel)
					.collect(Collectors.toList());

			return ResponseEntity.ok(
								CollectionModel.of(list, 
									linkTo(methodOn(CarController.class).findCarsByPersonId(personId)).withSelfRel()
										)
					);
		}
		
		@GetMapping("/car/{id}")
		public ResponseEntity<?> getCar(@PathVariable long id){
			return ResponseEntity.ok(
					carAssembler.toModel(carRepository.findById(id).get())
								
					);
		}
		
		@PostMapping("/car/save/{personId}")
		public ResponseEntity<EntityModel<Car>> saveCar(@RequestBody @Valid Car car, @PathVariable long personId){
			car.setPerson(personRepository.findById(personId).get());
			return ResponseEntity.ok(
					carAssembler.toModel(carRepository.save(car))
					
					);	
		}
		
		@PutMapping("/update/car/{id}/person/{personId}")
		public ResponseEntity<?> updateCar(@PathVariable long id, @RequestBody @Valid Car newCar, @PathVariable long personId){
			Car c = carRepository.findById(id)
					.map(car -> {
						car.setBrand(newCar.getBrand());
						car.setCapacity(newCar.getCapacity());
						return carRepository.save(car);
					})
					.orElseGet(()-> {
									newCar.setPerson(personRepository.findById(personId).get());
									newCar.setId(id);
								
									return carRepository.save(newCar);
						} );
			return ResponseEntity.ok(carAssembler.toModel(c));		
		}
		
		@DeleteMapping("/delete/car/{id}")
		public void deleteCar(@PathVariable long id) {
			carRepository.deleteById(id);
		}
		
		
		
		
}
