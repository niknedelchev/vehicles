package com.nn.vehicles.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.BrandModel;
import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.service.EmployeeService;
import com.nn.vehicles.service.QualificationService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.UserService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private QualificationService qualificationService;
	
	@Autowired
	private RepairShopService repairShopService;
	@Autowired
	private UserService userService;
	
	
	//admin
	@GetMapping(path = "/employees")
	public String showEmployeesPage(Model model) {
		List<Employee> employees = employeeService.findAll();
		model.addAttribute("employees", employees);
		
		return "employees/employees";
	}
	
	//admin
	@GetMapping(path = "/employees/add")
	public String showAddEmployeesPage(Model model) {
		model.addAttribute("employee", new Employee());
		List<Qualification> qualifications = qualificationService.findAll();
		model.addAttribute("qualifications", qualifications);
		
		List<RepairShop> repairShops = repairShopService.findAll();
		model.addAttribute("repairShops", repairShops);
		
		return "employees/employees-add";
	}
	
	//admin
	@PostMapping(path="/employees/add")
	public String addEmployee(@ModelAttribute Employee employee) {
		employeeService.save(employee);
		
		return "redirect:/employees";
	}
	
	//admin
	@GetMapping("/employees/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Employee employee = employeeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
	    
	    model.addAttribute("employee", employee);
	    
	    List<Qualification> qualifications = qualificationService.findAll();
		model.addAttribute("qualifications", qualifications);
		
		List<RepairShop> repairShops = repairShopService.findAll();
		model.addAttribute("repairShops", repairShops);
		
	    return "employees/employees-edit";
	}

	//admin
	@PostMapping("/employees/edit/{id}")
	public String updateEmployee(@ModelAttribute Employee employee) {
		
		try {
			employeeService.update(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/employees";
	}
	
	//admin    
	@GetMapping("/employees/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id, Model model) {
		Employee employee = employeeService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		employeeService.delete(employee);
	    return "redirect:/employees";
	}
	//employee
	@GetMapping(path = "/employee")
	public String showEmployeePage(Model model) {
		Employee employee = userService.getCurrentUser().getEmployee();
		model.addAttribute("employee", employee);
		
		return "employees/employee";
	}
	
	
}
