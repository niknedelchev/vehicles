package com.nn.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nn.vehicles.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>  {

}
