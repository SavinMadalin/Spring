package com.example.Spring;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	List<Car> findCarByBrand(String brand);
	List<Car> findCarByPersonId(long personId);
	@Async
	@Query("Select c from Car c where c.capacity=?1")
	CompletableFuture<List<Car>> findCarByCapacity(int capacity);
}
