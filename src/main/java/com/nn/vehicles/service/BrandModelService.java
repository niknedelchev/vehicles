package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.BrandModelRepository;
import com.nn.vehicles.model.BrandModel;

@Service
public class BrandModelService {

	@Autowired
	private BrandModelRepository repo;
	
	public Optional<BrandModel> findById(int id) {
		return repo.findById(id);
	}
	
	public List<BrandModel> findAll(){
		return repo.findAll();
	}
	
	public void save(BrandModel brandModel) {
		repo.save(brandModel);
	}
	
	public void delete(BrandModel brandModel) {
		repo.delete(brandModel);
	}
	
	
	public void update(BrandModel brandModel) throws Exception {
		BrandModel tempBrandModel = repo.findById(brandModel.getId()).orElse(null);
		
		if (tempBrandModel != null) {
			tempBrandModel.setName(brandModel.getName());
			tempBrandModel.setBrand(brandModel.getBrand());
			repo.save(tempBrandModel);
		} else {
			throw new Exception("Brand model not found");
		}
	}
}
