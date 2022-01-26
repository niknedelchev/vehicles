package com.nn.vehicles.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nn.vehicles.model.BrandModel;
import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.model.Vehicle;
import com.nn.vehicles.service.BrandModelService;
import com.nn.vehicles.service.CustomerService;
import com.nn.vehicles.service.UserService;
import com.nn.vehicles.service.VehicleService;

@Controller
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private BrandModelService brandModelService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	

	
	//customer
	@GetMapping(path = "/vehicles/customer")
	public String showVehiclesByCustomerOperationsPage(Model model) {
		Customer customer = userService.getCurrentUser().getCustomer();
		
		List<Vehicle> vehicles = vehicleService.findAllbyCustomer(customer.getId());
		model.addAttribute("vehicles", vehicles);
		
		model.addAttribute("customer", customer);
	
		return "vehicles/vehicles-customer";
	}
	//customer	
	@GetMapping(path = "/vehicles/customer/add")
	public String showAddVehicleByCustomerPage(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		List<BrandModel> brandModels = brandModelService.findAll();
		Customer customer = userService.getCurrentUser().getCustomer();
		
		model.addAttribute("brandModels", brandModels);
		model.addAttribute("customer", customer);
		
		return "vehicles/vehicles-customer-add";
	}
	
	//customer	
	@PostMapping(path = "/vehicles/customer/add")
	public String addVehicleByCustomer(@Valid @ModelAttribute("vehicle") Vehicle vehicle,  BindingResult result) {
		 if (result.hasErrors()) {
			 return "redirect:/vehicles/customer/add";
		    }
		
		vehicleService.save(vehicle);
		return "redirect:/vehicles/customer/";
	}
	//customer	
	@GetMapping("/vehicles/customer/edit/{id}")
	public String showUpdateFormByCustomer(@PathVariable("id") int id, Model model) {
		Vehicle vehicle = vehicleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id:" + id));
	    model.addAttribute("vehicle", vehicle);
	
	    List<BrandModel> brandModels = brandModelService.findAll();
		model.addAttribute("brandModels", brandModels);
	
		return "vehicles/vehicles-customer-edit";
	}
	//customer	
	@PostMapping("/vehicles/customer/edit")
	public String updateVehicleByCustomer(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result) throws Exception{
		if (result.hasErrors()) {
			 return "redirect:/vehicles/customer";	
		    }
		vehicleService.update(vehicle);
		return "redirect:/vehicles/customer";
	}
	
	//customer	
	//Report
	@GetMapping(path = "/vehicles/customer/list")
	public String showVehiclesByCustomerList(Model model) {
		Customer customer = userService.getCurrentUser().getCustomer();
		
		List<Vehicle> vehicles = vehicleService.findAllbyCustomer(customer.getId());
		model.addAttribute("vehicles", vehicles);
		model.addAttribute("customer", customer);
	
		return "vehicles/vehicles-customer-list";
	}
	
	//admin
	@GetMapping(path = "/vehicles")
	public String showVehiclesPage(Model model) {
		List<Vehicle> vehicles = vehicleService.findAll();
		model.addAttribute("vehicles", vehicles);

		return "vehicles/vehicles";
	}
	//admin
	@GetMapping(path = "/vehicles/add")
	public String showAddVehiclePage(Model model) {
		model.addAttribute("vehicle", new Vehicle());
		List<BrandModel> brandModels = brandModelService.findAll();
		List<Customer> customers = customerService.findAll();
		model.addAttribute("brandModels", brandModels);
		model.addAttribute("customers", customers);
		
		return "vehicles/vehicles-add";
	}
	//admin
	@PostMapping(path = "/vehicles/add")
	public String addVehicle(@ModelAttribute Vehicle vehicle) {
		vehicleService.save(vehicle);
		
		return "redirect:/vehicles";
	}
	//admin
	@GetMapping("/vehicles/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Vehicle vehicle = vehicleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id:" + id));
	    model.addAttribute("vehicle", vehicle);
	
	    List<BrandModel> brandModels = brandModelService.findAll();
		List<Customer> customers = customerService.findAll();
		model.addAttribute("brandModels", brandModels);
		model.addAttribute("customers", customers);
	
	    return "vehicles/vehicles-edit";
	}
	//admin
	@PostMapping("/vehicles/edit/{id}")
	public String updateVehicle(@ModelAttribute Vehicle vehicle) {
		try {
			vehicleService.update(vehicle);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/vehicles";
	}
	//admin && customer
	@GetMapping("/vehicles/delete/{id}")
	public String deleteVehicle(@PathVariable("id") int id, Model model) {
		Vehicle vehicle  = vehicleService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle Id:" + id));
		vehicleService.delete(vehicle);
	    return "redirect:/";
	}
	//Admin report
	@GetMapping(path = "/vehicles/customer/{customerId}")
	public String showVehiclesByCustomerAdmin(Model model, @PathVariable int customerId) {
		Customer customer = customerService.findById(customerId).get();
		
		List<Vehicle> vehicles = vehicleService.findAllbyCustomer(customer.getId());
		model.addAttribute("vehicles", vehicles);
		model.addAttribute("customer", customer);
	
		return "vehicles/vehicles-customer-list";
	}

}
