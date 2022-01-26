package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.BrandRepository;
import com.nn.vehicles.model.Brand;
import com.nn.vehicles.model.BrandModel;

@Service
public class BrandService {

	@Autowired
	private BrandRepository repo;
	
	public Optional<Brand> findById(int id) {
		return repo.findById(id);
	}
	
	public List<Brand> findAll(){
		return repo.findAll();
	}
	
	public void save(Brand brand) {
		repo.save(brand);
	}
	
	
	public void delete(Brand brand) {
		repo.delete(brand);
	}
	
	public void update(Brand brand) throws Exception {
		Brand tempBrand = repo.findById(brand.getId()).orElse(null);
		
		if (tempBrand != null) {
			tempBrand.setName(brand.getName());
			repo.save(tempBrand);
		} else {
			throw new Exception("Brand not found");
		}
	}
}
