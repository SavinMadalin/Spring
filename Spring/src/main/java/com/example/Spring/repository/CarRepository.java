package com.example.Spring.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import com.example.Spring.models.Car;
import com.example.Spring.models.CarRequest;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findCarByBrand(String brand);
	List<Car> findCarByPersonId(long personId);
	List<Car> findCarByCapacity(int capacity);
}
