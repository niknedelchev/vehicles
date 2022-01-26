package com.nn.vehicles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.nn.vehicles.model.Brand;
import com.nn.vehicles.service.BrandService;

@Controller
public class BrandController {

	//admin
	@Autowired
	private BrandService brandService;
	
	@GetMapping(path = "brands")
	public String showBrandsPage(Model model) {
		
		List<Brand> brands = brandService.findAll();
		model.addAttribute("brands", brands);
		
		return "brands/brands";
	}
	
	@GetMapping(path = "brands/add")
	public String showAddBrandPage(Model model) {
		model.addAttribute("brand", new Brand());
		
		return "brands/brands-add";
	}
	
	@PostMapping(path = "brands/add")
	public String addBrand(@ModelAttribute Brand brand) {
		brandService.save(brand);
		
		return "redirect:/brands";
	}
	
	@GetMapping("brands/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Brand brand = brandService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
	    
	    model.addAttribute("brand", brand);
	    return "brands/brands-edit";
	}
	
	@PostMapping("brands/edit/{id}")
	public String updateBrand(@ModelAttribute Brand brand) {

		try {
			brandService.update(brand);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return "redirect:/brands";
	}
	    
	@GetMapping("/brands/delete/{id}")
	public String deleteBrand(@PathVariable("id") int id, Model model) {
	    Brand brand = brandService.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
	    brandService.delete(brand);
	    return "redirect:/brands";
	}
	
}
