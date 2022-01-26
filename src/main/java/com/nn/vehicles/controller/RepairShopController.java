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
import com.nn.vehicles.model.Repair;
import com.nn.vehicles.model.RepairShop;
import com.nn.vehicles.service.BrandService;
import com.nn.vehicles.service.RepairShopService;

@Controller
public class RepairShopController {

	@Autowired
	private RepairShopService repairShopService;
	
	@Autowired
	private BrandService brandService;
	
	//admin
	@GetMapping(path = "/repairshops")
	public String showRepairShopsPage(Model model) {
		List<RepairShop> repairShops = repairShopService.findAll();
		model.addAttribute("repairShops", repairShops);
		
		return "repairshops/repairshops";
	}
	
	//admin
	@GetMapping(path = "/repairshops/add")
	public String showRepairShopAddPage(Model model) {
		model.addAttribute("repairShop", new RepairShop());
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		return "repairshops/repairshops-add";
	}
	
	//admin
	@PostMapping(path = "/repairshops/add")
	public String addRepairShop(@ModelAttribute RepairShop repairShop) {
		repairShopService.save(repairShop);
		
		return "redirect:/repairshops";
	}
	
	//admin
	@GetMapping("/repairshops/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		RepairShop repairShop = repairShopService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid repair shop Id:" + id));
	    model.addAttribute("repairShop", repairShop);
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
	
	    return "repairshops/repairshops-edit";
	}
	
	//admin
	@PostMapping("/repairshops/edit/{id}")
	public String updateRepair(@ModelAttribute RepairShop repairShop) {
		try {
			repairShopService.update(repairShop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/repairshops";
	}
	
	//admin    
	@GetMapping("/repairshops/delete/{id}")
	public String deleteRepair(@PathVariable("id") int id, Model model) {
		RepairShop repairShop = repairShopService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid repair shop Id:" + id));
		repairShopService.delete(repairShop);
	    return "redirect:/repairshops";
	}
	
}
