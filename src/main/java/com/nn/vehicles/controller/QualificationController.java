package com.nn.vehicles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;
import com.nn.vehicles.model.RepairType;
import com.nn.vehicles.service.QualificationService;
import com.nn.vehicles.service.RepairTypeService;

@Controller
public class QualificationController {

	@Autowired
	private QualificationService qualificationService;
	@Autowired
	private RepairTypeService repairTypeService;
	
	@GetMapping(path = "/qualifications")
	public String showQualificationsPage(Model model) {
		List<Qualification> qualifications = qualificationService.findAll();
		model.addAttribute("qualifications", qualifications);
		
		return "qualifications/qualifications";
	}
	
	@GetMapping(path = "/qualifications/add")
	public String showAddQualificationsPage(Model model) {
		model.addAttribute("qualification", new Qualification());
		List<RepairType> repairTypes = repairTypeService.findAll();
		model.addAttribute("repairTypes", repairTypes);
		
		return "qualifications/qualifications-add";
	}
	
	@PostMapping(path = "/qualifications/add")
	public String addQualification(@ModelAttribute Qualification qualification) {
		qualificationService.save(qualification);
		
		return "redirect:/qualifications";
	}
	
	@GetMapping("/qualifications/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Qualification qualification = qualificationService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid qualification Id:" + id));
	    model.addAttribute("qualification", qualification);
		List<RepairType> repairTypes = repairTypeService.findAll();
		model.addAttribute("repairTypes", repairTypes);

	    return "qualifications/qualifications-edit";
	}
	
	@PostMapping("/qualifications/edit/{id}")
	public String updateEmployee(@ModelAttribute Qualification qualification) {
		
		try {
			qualificationService.update(qualification);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/qualifications";
	}
	    
	@GetMapping("/qualifications/delete/{id}")
	public String deleteEmployee(@PathVariable("id") int id, Model model) {
		Qualification qualification = qualificationService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid qualification Id:" + id));
		qualificationService.delete(qualification);
	    return "redirect:/qualifications";
	}
	
	
}
