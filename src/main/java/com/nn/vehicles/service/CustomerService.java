package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.CustomerRepository;
import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;
	
	public Optional<Customer> findById(int id) {
		return repo.findById(id);
	}
	
	
	public List<Customer> findAll(){
		return repo.findAll();
	}
	
	public void save(Customer customer) {
		repo.save(customer);
	}
	
	public void delete(Customer customer) {
		repo.delete(customer);
	}
	
	public void update(Customer customer) throws Exception {
		Customer tempCustomer = repo.findById(customer.getId()).orElse(null);
		
		if (tempCustomer != null) {
			tempCustomer.setFirstName(customer.getFirstName());
			tempCustomer.setLastName(customer.getLastName());
			repo.save(tempCustomer);
		} else {
			throw new Exception("Customer not found");
		}
	}
}
