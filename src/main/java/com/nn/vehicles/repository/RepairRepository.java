package com.nn.vehicles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.vehicles.model.Repair;

public interface RepairRepository extends JpaRepository<Repair, Integer>  {
	List<Repair> findByVehicleId(int vehicleId);
}
