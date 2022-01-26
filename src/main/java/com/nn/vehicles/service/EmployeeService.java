package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.EmployeeRepository;
import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	public Optional<Employee> findById(int id) {
		return repo.findById(id);
	}
	
	
	public List<Employee> findAll(){
		return repo.findAll();
	}
	
	public void save(Employee employee) {
		repo.save(employee);
	}
	
	public void delete(Employee employee) {
		repo.delete(employee);
	}
	
	public void update(Employee employee) throws Exception {
		Employee tempEmployee = repo.findById(employee.getId()).orElse(null);
		
		if (tempEmployee != null) {
			tempEmployee.setFirstName(employee.getFirstName());
			tempEmployee.setLastName(employee.getLastName());
			tempEmployee.setQualification(employee.getQualification());
			tempEmployee.setRepairShop(employee.getRepairShop());
			repo.save(tempEmployee);
		} else {
			throw new Exception("Employee not found");
		}
	}
}
