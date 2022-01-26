package com.nn.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.vehicles.model.RepairType;

public interface RepairTypeRepository extends JpaRepository<RepairType, Integer> {

}
