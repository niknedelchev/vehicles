package com.nn.vehicles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nn.vehicles.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
	
	@Query(
			value="SELECT * FROM vehicles WHERE customer_id = ?1",
			nativeQuery = true)
	public List<Vehicle> findAllbyCustomer(Integer customerID);
	
}
