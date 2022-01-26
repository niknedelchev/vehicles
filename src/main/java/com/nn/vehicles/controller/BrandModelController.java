package com.nn.vehicles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.BrandModel;
import com.nn.vehicles.service.BrandModelService;
import com.nn.vehicles.service.BrandService;

@Controller
public class BrandModelController {

	@Autowired
	private BrandModelService brandModelService;
	@Autowired
	private BrandService brandService;
	
	//admin
	@GetMapping(path = "brandmodels")
	public String showBrandModelsPage(Model model) {
		
		List<BrandModel> brandModels = brandModelService.findAll();
		model.addAttribute("brandModels", brandModels);
		
		return "brandmodels/brandmodels";
	}
	
	@GetMapping(path="brandmodels/add")
	public String showBrandModelAddPage(Model model) {
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		model.addAttribute("brandModel", new BrandModel());
		
		return "brandmodels/brandmodels-add";
	}
	
	@PostMapping(path="brandmodels/add")
	public String addBrandModel(@ModelAttribute BrandModel brandModel) {
		
		brandModelService.save(brandModel);
		return "redirect:/brandmodels";
	}
	
	@GetMapping("brandmodels/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		BrandModel brandModel = brandModelService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand model Id:" + id));
	    
	    model.addAttribute("brandModel", brandModel);
	    
	    List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		
	    return "brandmodels/brandmodels-edit";
	}
	
	@PostMapping("/brandmodels/edit/{id}")
	public String updateBrandModels(@ModelAttribute BrandModel brandModel) {
		
		try {
			brandModelService.update(brandModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return "redirect:/brandmodels";
	}
	    
	@GetMapping("/brandmodels/delete/{id}")
	public String deleteBrandModels(@PathVariable("id") int id, Model model) {
		BrandModel brandModel = brandModelService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid brand model Id:" + id));
		brandModelService.delete(brandModel);
	    return "redirect:/brandmodels";
	}
	
}
