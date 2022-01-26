package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.RepairShopRepository;
import com.nn.vehicles.model.Repair;
import com.nn.vehicles.model.RepairShop;

@Service
public class RepairShopService {

	@Autowired
	private RepairShopRepository repo;
	
	public Optional<RepairShop> findById(int id) {
		return repo.findById(id);
	}
	
	public List<RepairShop> findAll(){
		return repo.findAll();
	}
	
	public void save(RepairShop repairShop) {
		repo.save(repairShop);
	}
	
	public void delete(RepairShop repairShop) {
		repo.delete(repairShop);
	}
	
	public void update(RepairShop repairShop) throws Exception {
		RepairShop tempRepairShop = repo.findById(repairShop.getId()).orElse(null);
		
		if (tempRepairShop != null) {
			tempRepairShop.setName(repairShop.getName());
			tempRepairShop.setExclusiveBrand(repairShop.getExclusiveBrand());
			repo.save(tempRepairShop);
		} else {
			throw new Exception("Repair shop not found");
		}
	}
}
