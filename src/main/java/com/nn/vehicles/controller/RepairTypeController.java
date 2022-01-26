package com.nn.vehicles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nn.vehicles.model.RepairType;
import com.nn.vehicles.service.RepairTypeService;

@Controller
public class RepairTypeController {

	@Autowired
	private RepairTypeService repairTypeService;
	//admin
	@GetMapping(path = "/repairtypes")
	public String showRepairsPage(Model model) {
		List<RepairType> repairTypes = repairTypeService.findAll();
		model.addAttribute("repairTypes", repairTypes);
		
		return "repairtypes/repairtypes";
	}
	
	@GetMapping(path = "/repairtypes/add")
	public String showRepairTypesAddPage(Model model) {
		model.addAttribute("repairType", new RepairType());
		
		return "repairtypes/repairtypes-add";
	}
	
	@PostMapping(path = "/repairtypes/add")
	public String addRepairType(@ModelAttribute RepairType repairType) {
		repairTypeService.save(repairType);
		
		return "redirect:/repairtypes";
	}
	
	@GetMapping("/repairtypes/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		RepairType repairType = repairTypeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid repair type Id:" + id));
	    model.addAttribute("repairType", repairType);
	    return "repairtypes/repairtypes-edit";
	}
	
	@PostMapping("/repairtypes/edit/{id}")
	public String updateRepair(@ModelAttribute RepairType repairType) {
		
		try {
			repairTypeService.update(repairType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/repairtypes";
	}
	    
	@GetMapping("/repairtypes/delete/{id}")
	public String deleteRepair(@PathVariable("id") int id, Model model) {
		RepairType repairType = repairTypeService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid repair type Id:" + id));
		repairTypeService.delete(repairType);
	    return "redirect:/repairtypes";
	}
}
