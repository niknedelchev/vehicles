package com.nn.vehicles.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.service.QualificationService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private RepairShopService repairShopService;

	@GetMapping(path = "/admin")
	public String showAdminPage(Model model) {
		String userName = userService.getCurrentUser().getUsername();
		String role = userService.getCurrentUser().getRole().toString();
		
		model.addAttribute("userName", userName);
		model.addAttribute("role", role);
	
		return "admin/admin";
	}
	
	@GetMapping(path = "/register/employee")
	public String showRegisterEmployeePage(Model model) {
		List<Qualification> qualifications = qualificationService.findAll();
		model.addAttribute("qualifications", qualifications);
		
		List<RepairShop> repairShops = repairShopService.findAll();
		model.addAttribute("repairShops", repairShops);
		
		return "registration-employee";
	}
	
	
	@GetMapping(path = "/register/admin")
	public String showRegisterAdminPage() {
		
		return "registration-admin";
	}
	
	 @PostMapping(path = "/register/employee")
	    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username
	                           ,@RequestParam String password, @RequestParam int qualification, @RequestParam int repairShop) {
	        
		 	Qualification qualif = qualificationService.findById(qualification).get();
		 	RepairShop rShop = repairShopService.findById(repairShop).get();
		 	
	    	userService.registerEmployee(firstName, lastName, username, password, qualif, rShop);
	     
	    	return "admin/admin";
	    }
	 
	 @PostMapping(path = "/register/admin")
	    public String register(@RequestParam String username ,@RequestParam String password) {
	        
	    	userService.registerAdmin(username, password);
	     
	    	return "admin/admin";
	    }
}
