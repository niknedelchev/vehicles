package com.nn.vehicles.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.hql.internal.classic.GroupByParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nn.vehicles.model.Customer;
import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.Repair;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.model.RepairType;
import com.nn.vehicles.model.User;
import com.nn.vehicles.model.Vehicle;
import com.nn.vehicles.repository.VehicleRepository;
import com.nn.vehicles.service.CustomerService;
import com.nn.vehicles.service.EmployeeService;
import com.nn.vehicles.service.RepairService;
import com.nn.vehicles.service.RepairShopService;
import com.nn.vehicles.service.RepairTypeService;
import com.nn.vehicles.service.UserService;
import com.nn.vehicles.service.VehicleService;


@Controller
public class RepairController {

	@Autowired
	private RepairService repairService;
	
	@Autowired
	private RepairShopService repairShopService;
	
	@Autowired
	private RepairTypeService repairTypeService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private UserService userService;
	

	//Customer reports
	@GetMapping(path = "repairs/customer/vehicle-history")
	public String showCustomerVehicleHistory(Model model, @RequestParam(required=false) String vehicleId) {

		Customer customer = userService.getCurrentUser().getCustomer();
		model.addAttribute("customer", customer);
		
		if(vehicleId != null) {
			int id = Integer.parseInt(vehicleId);

			List<Repair> vehicleRepairsHistory = repairService.findByVehicleId(id);
			
			model.addAttribute("repairs", vehicleRepairsHistory);
			if(!vehicleRepairsHistory.isEmpty())
				model.addAttribute("vehicles", vehicleRepairsHistory.get(0).getVehicle());
			
		}
		
		return "repairs/repairs-customer-vehicle-history";
	}
	
	//Available to customers
	@GetMapping(path = "/repairs/customer")
	public String showRepairsByCustomerPage(Model model) {
		Customer customer = userService.getCurrentUser().getCustomer();
		
		List<Repair> repairs = customer.getVehicles().stream()
							.flatMap(v->v.getRepairs().stream())
							.sorted(
								new Comparator<Repair>() {
									@Override
									public int compare(Repair lhs,Repair rhs) {
										return lhs.getId() - rhs.getId();
									}
								})
							.collect(Collectors.toList());
				
	
		model.addAttribute("repairs", repairs);
		
		return "repairs/repairs-customer";
	}
	
	//Available to customers: book a repair service
	@GetMapping(path = "/repairs/book")
	public String showBookRepairByCustomerPage(@RequestParam(required=false) String repairShop, @RequestParam(required=false) String vehicle, Model model) {
		model.addAttribute("repair", new Repair());
		Customer customer = userService.getCurrentUser().getCustomer();
		List<RepairShop> repairShops = repairShopService.findAll();
		
		model.addAttribute("repairShops", repairShops);
		model.addAttribute("customerVehicles", customer.getVehicles());
	
		if(vehicle!=null)
		{
			Optional<Vehicle> selectedVehicle = vehicleService.findById(Integer.parseInt(vehicle));
			model.addAttribute("selectedVehicle", selectedVehicle);
			
			//check if selected vehicle is in exclusive list of repairshop. 
			//If not, remove repairshop from list.
			for (int i=0; i<repairShops.size(); i++) {
				if(repairShops.get(i).getExclusiveBrand()!=null)
					if(repairShops.get(i).getExclusiveBrand().getName().equalsIgnoreCase(selectedVehicle.get().getBrandModel().getBrand().getName()))
						continue;
					else
						repairShops.remove(i);
			}
			//update repairshop list considering the exclusive brand constraint
			model.addAttribute("repairShops", repairShops);
		} 
		else {
			model.addAttribute("selectedVehicle", new ArrayList<Vehicle>());
		}
	
		if(repairShop!=null) {
			Optional<RepairShop> selectedShop = repairShopService.findById(Integer.parseInt(repairShop));
			model.addAttribute("selectedShop", selectedShop);
	
			Set<RepairType> repairTypesByRepairShop = new HashSet<>();
			if (!selectedShop.isEmpty()) {
				for( Employee emp :selectedShop.get().getEmployees()) {
					repairTypesByRepairShop.addAll(emp.getQualification().getRepairTypes());
				}	
			}
			
			model.addAttribute("repairTypesByRepairShop", repairTypesByRepairShop);
		
		} else {
			model.addAttribute("selectedShop", new ArrayList<RepairShop>());
		}
		
		return "repairs/repairs-customers-book";
	}
	//Available to customers: book a repair service
	@PostMapping(path = "/repairs/book")
	public String bookRepairByCustomer(@ModelAttribute Repair repair) {
		repairService.save(repair);
		
		return "redirect:/repairs/customer/";
	}

//////////////////////////////////////////////////////////////////////////////////////////	
	//Report employee
	@GetMapping(path = "/repairs/repairshop")
	public String showAllRepairsAtRepairShopPage(Model model) {
		
		List<Repair> repairs = userService.getCurrentUser()
								.getEmployee()
								.getRepairShop()
								.getRepairs()
								.stream()
								.sorted(
									new Comparator<Repair>() {
										@Override
										public int compare(Repair lhs,Repair rhs) {
											return lhs.getId() - rhs.getId();
										}
									})
								.collect(Collectors.toList());
		
		model.addAttribute("repairs", repairs);
		
		return "repairs/repairs";
	}
	
	//Available to employees: process repair services
	@GetMapping(path = "/repairs/process")
	public String showBookingsToBeProcessedPage(Model model) 
	{
		Employee employee = userService.getCurrentUser().getEmployee();
		List<Repair> repairs = employee.getRepairShop()
										.getRepairs().stream()
										.filter(r->!r.isProcessed())
										.collect(Collectors.toList());
		model.addAttribute("repairs", repairs);
		model.addAttribute("currentRepairShop", employee.getRepairShop());
		
		return "repairs/repairs-repairshop-process-bookings";
	}
	
	
	
	//Available to employees: process repair services
	@GetMapping(path = "/repairs/process/{id}")
	public String processBookingPage(@PathVariable("id") int id, Model model) 
	{
		Repair repair = repairService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid repair Id:" + id));
	    model.addAttribute("repair", repair);
		
		return "repairs/repairs-process";
	}
	
	//Available to employees: process repair services
	@PostMapping(path = "/repairs/process")
	public String processBooking(@RequestParam int id, @RequestParam String price) 
	{
		Repair repair = repairService.findById(id).get();
		repair.setPrice(Double.parseDouble(price));
		repair.setProcessed(true);
		
		repairService.save(repair);
		
		return "redirect:/repairs/process";
	}
	
	//Employee reports
	@GetMapping(path = "repairs/repairshop/vehicle-history")
	public String showRepairShopVehicleHistory(Model model, @RequestParam(required=false) String vehicleId) {

		RepairShop currentRepairShop = userService.getCurrentUser().getEmployee().getRepairShop();
		Set<Vehicle> allRepairedVehicles = currentRepairShop.getRepairs().stream().map(r->r.getVehicle()).collect(Collectors.toSet());
		model.addAttribute("vehicles", allRepairedVehicles);
		
		if(vehicleId != null) {
			int id = Integer.parseInt(vehicleId);

			List<Repair> vehicleRepairsHistory = currentRepairShop.getRepairs().stream()
			.filter(r->r.getVehicle().getId()==id)
			.collect(Collectors.toList());
			model.addAttribute("repairs", vehicleRepairsHistory);
			model.addAttribute("vehicle", vehicleRepairsHistory.get(0).getVehicle());
			model.addAttribute("currentRepairShop", currentRepairShop);
		}
		
		return "repairs/repairs-repairshop-vehicle-history";
	}
	
	//Employee reports
	@GetMapping(path = "/repairs/repairshop/repairs-by-type")
	public String showRepairShopServicesByType(Model model) {

		RepairShop currentRepairShop = userService.getCurrentUser().getEmployee().getRepairShop();
			
			Map<RepairType, Long> repairsByType = currentRepairShop.getRepairs().stream().collect(Collectors.groupingBy(Repair::getRepairType, Collectors.counting()));
			
			model.addAttribute("repairs", repairsByType);
			model.addAttribute("currentRepairShop", currentRepairShop);
		
		return "repairs/repairs-repairshop-repairs-by-type";
	}
	
	//Employee reports
	@GetMapping(path = "/repairs/repairshop/repairs-by-brand")
	public String showRepairShopServicesByBrand(Model model) {

		RepairShop currentRepairShop = userService.getCurrentUser().getEmployee().getRepairShop();
			
			Map<String, Integer> repairsByBrand = new HashMap<>();
			
			currentRepairShop.getRepairs().forEach(r->{
				String key = r.getVehicle().getBrandModel().getBrand().getName();
			
				if(repairsByBrand.containsKey(key)) {
					int value = repairsByBrand.get(key);
					repairsByBrand.put(key, value+1);
				} else {
					repairsByBrand.put(key, 1);
				}
						
			});
		
			model.addAttribute("repairs", repairsByBrand);
			model.addAttribute("currentRepairShop", currentRepairShop);
		
		return "repairs/repairs-repairshop-repairs-by-brand";
	}
	
	
	//Employee reports
	@GetMapping(path = "/repairs/repairshop/repairs-by-year")
	public String showRepairShopServicesByYear(Model model) {

		RepairShop currentRepairShop = userService.getCurrentUser().getEmployee().getRepairShop();
		
			Map<Integer, Integer> repairsByYear = new HashMap<>();
			
			currentRepairShop.getRepairs().forEach(r->{
				int key = r.getRepairDate().getYear();
			
				if(repairsByYear.containsKey(key)) {
					int value = repairsByYear.get(key);
					repairsByYear.put(key, value+1);
				} else {
					repairsByYear.put(key, 1);
				}
						
			});
		
			model.addAttribute("repairs", repairsByYear);
			model.addAttribute("currentRepairShop", currentRepairShop);
		
		return "repairs/repairs-repairshop-repairs-by-year";
	}
	
///////////////////////////////////////////////////////////////////////////
	//admin
	@GetMapping(path = "/repairs")
	public String showRepairsPage(Model model) {
		List<Repair> repairs = repairService.findAll();
		model.addAttribute("repairs", repairs);
		
		return "repairs/repairs";
	}
	//admin	
	@GetMapping(path = "/repairs/add")
	public String showRepairsAddPage(Model model) {
		model.addAttribute("repair", new Repair());

		List<RepairShop> repairShops = repairShopService.findAll();
		List<RepairType> repairTypes = repairTypeService.findAll();
		model.addAttribute("repairShops", repairShops);
		model.addAttribute("repairTypes", repairTypes);
		
		return "repairs/repairs-add";
	}
	
	//admin
	@PostMapping(path = "/repairs/add")
	public String addRepair(@ModelAttribute Repair repair) {
		repairService.save(repair);
		
		return "redirect:/repairs";
	}
	//admin
	@GetMapping("/repairs/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Repair repair = repairService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid repair Id:" + id));
	    model.addAttribute("repair", repair);
	    
		List<RepairShop> repairShops = repairShopService.findAll();
		List<RepairType> repairTypes = repairTypeService.findAll();
		model.addAttribute("repairShops", repairShops);
		model.addAttribute("repairTypes", repairTypes);
    
	    return "repairs/repairs-edit";
	}
	//admin
	@PostMapping("/repairs/edit/{id}")
	public String updateRepair(@ModelAttribute Repair repair) throws Exception{
		repairService.update(repair);
	    return "redirect:/repairs";
	}
	//admin
	@GetMapping("/repairs/delete/{id}")
	public String deleteRepair(@PathVariable("id") int id, Model model) {
		Repair repair = repairService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid repair Id:" + id));
		repairService.delete(repair);
	    return "redirect:/repairs";
	}

	
	
}
