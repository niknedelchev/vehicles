package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.VehicleRepository;
import com.nn.vehicles.model.Vehicle;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repo;
	
	public Optional<Vehicle> findById(int id) {
		return repo.findById(id);
	}
	
	public List<Vehicle> findAllbyCustomer(Integer customerID){
		return repo.findAllbyCustomer(customerID);
	}
	
	public List<Vehicle> findAll(){
		return repo.findAll();
	}
	
	public void save(Vehicle vehicle) {
		repo.save(vehicle);
	}
	
	public void delete(Vehicle vehicle) {
		repo.delete(vehicle);
	}
	
	public void update(Vehicle vehicle) throws Exception {
		Vehicle tempVehicle = repo.findById(vehicle.getId()).orElse(null);
		
		if (tempVehicle != null) {
			tempVehicle.setBrandModel(vehicle.getBrandModel());
			tempVehicle.setCustomer(vehicle.getCustomer());
			tempVehicle.setProductionYear(vehicle.getProductionYear());
			tempVehicle.setRegistrationPlate(vehicle.getRegistrationPlate());
			repo.save(tempVehicle);
		} else {
			throw new Exception("Vehicle not found");
		}
	}
}
