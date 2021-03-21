package com.example.Spring.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Spring.assemblers.CarAssembler;
import com.example.Spring.controllers.CarController;
import com.example.Spring.exceptions.CarNotFoundException;
import com.example.Spring.models.Car;
import com.example.Spring.models.CarRequest;
import com.example.Spring.repository.CarRepository;
import com.example.Spring.repository.PersonRepository;

@Service
public class CarService {

		@Autowired
		private CarRepository carRepository;
		
		@Autowired 
		private CarAssembler carAssembler;
		
		@Autowired
		private PersonRepository personRepository;
		
		
		public CollectionModel<EntityModel<Car>>  getAllCars() {
			List<EntityModel<Car>> list = carRepository.findAll().stream()
					.map(carAssembler::toModel)
					.collect(Collectors.toList());
			CollectionModel<EntityModel<Car>> collection = CollectionModel.of(list, 
					linkTo(methodOn(CarController.class).findAllCars()).withSelfRel()
					);
			return collection;
		}
		
		public CollectionModel<EntityModel<Car>> getCarsByBrand(String brand){
			List<EntityModel<Car>> list = carRepository.findCarByBrand(brand).stream()
					.map(carAssembler::toModel)
					.collect(Collectors.toList());
			CollectionModel<EntityModel<Car>> collection = CollectionModel.of(list, 
					linkTo(methodOn(CarController.class).findCarsByBrand(brand)).withSelfRel()
					);
			return collection;
		}
		
		public CollectionModel<EntityModel<Car>> getCarsByPersonId(long personId){
			List<EntityModel<Car>> list = carRepository.findCarByPersonId(personId).stream()
					.map(carAssembler::toModel)
					.collect(Collectors.toList());
				CollectionModel<EntityModel<Car>> collection = CollectionModel.of(list, 
						linkTo(methodOn(CarController.class).findCarsByPersonId(personId)).withSelfRel()
						);
				return collection;
		}
		
		public CollectionModel<EntityModel<Car>> getCarsByCapacity(int capacity){
			List<Car> list= carRepository.findCarByCapacity(capacity);
			List<EntityModel<Car>> entity = new ArrayList<>();
						entity = list.stream()
												.map(carAssembler::toModel)
												.collect(Collectors.toList());
	
			CollectionModel<EntityModel<Car>> collection = CollectionModel.of(entity,
					linkTo(methodOn(CarController.class).findCarsByCapacity(capacity)).withSelfRel());
			return collection;
		}
		
		public EntityModel<Car> findCar(long id){
			Car car = carRepository.findById(id)
						.orElseThrow(() -> new CarNotFoundException(id));
			
			return carAssembler.toModel(car);
		}
		
		public String createCar(CarRequest carRequest, long personId){
			Car car = new Car();
			car.setBrand(carRequest.getBrand());
			car.setCapacity(carRequest.getCapacity());
			car.setPerson(personRepository.findById(personId).get());
		     carAssembler.toModel(carRepository.save(car));
		     return "Car was saved";
		}
		
		public EntityModel<Car>	replaceCar(CarRequest carRequest, long id, long personId){
			Car newCar = new Car();
			newCar.setBrand(carRequest.getBrand());
			newCar.setCapacity(carRequest.getCapacity());
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
			return carAssembler.toModel(c);	
		}
		
		public String removeCar(long id) {
			carRepository.deleteById(id);
			return "Car with " + id + " was removed";
		}
}
