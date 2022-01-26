package com.nn.vehicles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nn.vehicles.service.BrandModelService;
import com.nn.vehicles.service.BrandService;
import com.nn.vehicles.service.CustomerService;
import com.nn.vehicles.service.EmployeeService;
import com.nn.vehicles.service.QualificationService;
import com.nn.vehicles.service.RepairService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;
import com.nn.vehicles.service.VehicleService;

@Controller
public class IndexController {
	
	@Autowired
	private BrandModelService brandModelService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private RepairService repairService;
	@Autowired
	private RepairShopService repairShopService;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/")
	public String showIndexPage(Model model) {
		model.addAttribute("brandModels", brandModelService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("customers", customerService.findAll());
		model.addAttribute("employees", employeeService.findAll());
		model.addAttribute("qualifications", qualificationService.findAll());
		model.addAttribute("repairs", repairService.findAll());
		model.addAttribute("repairShops", repairShopService.findAll());
		model.addAttribute("vehicles", vehicleService.findAll());
		
		return "index";
	}

	
	@GetMapping(path = "login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping(path = "register")
	public String showRegisterPage() {
		return "registration";
	}
	
    @PostMapping(path = "/register")
    public String register(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String username
                           ,@RequestParam String password) {
        
    	userService.register(firstName, lastName, username, password);
     
       return "redirect:/";
    }
	
}
