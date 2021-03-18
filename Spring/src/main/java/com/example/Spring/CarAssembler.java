package com.example.Spring;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CarAssembler implements RepresentationModelAssembler<Car, EntityModel<Car>> {

	@Override
	public EntityModel<Car> toModel(Car car) {
			return EntityModel.of(car,
					linkTo(methodOn(CarController.class).findCarsByPersonId(car.getPerson().getId())).withRel("cars by person"),
					linkTo(methodOn(CarController.class).findCarsByBrand(car.getBrand())).withRel("cars by brand"),
					linkTo(methodOn(CarController.class).findAllCars()).withRel("all cars"),
					linkTo(methodOn(CarController.class).findCarsByCapacity(car.getCapacity())).withRel("cars by capacity"),
					linkTo(methodOn(CarController.class).getCar(car.getId())).withSelfRel()
					);
	}

}
