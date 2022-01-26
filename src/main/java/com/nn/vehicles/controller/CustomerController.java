package com.nn.vehicles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.BrandModel;
import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.service.CustomerService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private RepairShopService repairShopService;
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping(path = "/customer")
	public String showCustomerProfilePage(Model model) {
		
		Customer customer = userService.getCurrentUser().getCustomer();
		model.addAttribute("customer", customer);
		
		return "customers/customer";
	}
	
	
	//only admin
	@GetMapping(path = "/customers")
	public String showCustomersPage(Model model) {
		
		List<Customer> customers = customerService.findAll();
		model.addAttribute("customers", customers);
		
		return "customers/customers";
	}
	
	//admin
	@GetMapping(path = "/customers/add")
	public String showCustomersAddPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "customers/customers-add";
	}
	//admin
	@PostMapping(path ="/customers/add")
	public String addCustomer(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return "redirect:/customers";
	}
	//admin
	@GetMapping("/customers/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Customer customer = customerService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
		model.addAttribute("customer", customer);
	    
	    return "customers/customers-edit";
	}
	//admin
	@PostMapping("customers/edit/{id}")
	public String updateCustomers(@ModelAttribute Customer customer) throws Exception {
			customerService.update(customer);
	    return "redirect:/customers";
	}
	//admin    
	@GetMapping("customers/delete/{id}")
	public String deleteBrandModels(@PathVariable("id") int id, Model model) {
		Customer customer = customerService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid customer Id:" + id));
		customerService.delete(customer);
	    return "redirect:/customers";
	}
	
}
