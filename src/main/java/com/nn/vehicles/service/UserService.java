package com.nn.vehicles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.model.Role;
import com.nn.vehicles.model.User;
import com.nn.vehicles.repository.CustomerRepository;
import com.nn.vehicles.repository.EmployeeRepository;
import com.nn.vehicles.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void register(String firstName,String  lastName, String username,String password) {
		
	    User user = new User(username,passwordEncoder.encode(password), Role.CUSTOMER , null, null);
	    this.userRepository.save(user);

	    Customer customer = new Customer(firstName, lastName, null, user);
	    this.customerRepository.save(customer);
	}
	
	public Customer registerCustomer(String firstName,String  lastName, String username,String password) {
		
	    User user = new User(username,passwordEncoder.encode(password), Role.CUSTOMER , null, null);
	    this.userRepository.save(user);

	    Customer customer = new Customer(firstName, lastName, null, user);
	    this.customerRepository.save(customer);
	   
	    return customer;
	}
	
	public Employee registerEmployee(String firstName,String  lastName, String username,String password, Qualification qualification, RepairShop repairShop) {
		
	    User user = new User(username,passwordEncoder.encode(password), Role.EMPLOYEE , null, null);
	    this.userRepository.save(user);

	    Employee employee = new Employee(firstName, lastName, qualification, repairShop, user);
	    this.employeeRepository.save(employee);
	    
	    return employee;
	}
	
	public void registerAdmin(String username,String password) {
		
	    User user = new User(username,passwordEncoder.encode(password), Role.ADMIN , null, null);
	    this.userRepository.save(user);

	}
	
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		User currentUser = userRepository.findByUsername(userDetails.getUsername());
		
		return currentUser;
	}
}
