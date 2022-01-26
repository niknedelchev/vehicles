package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.RepairTypeRepository;
import com.nn.vehicles.model.RepairType;

@Service
public class RepairTypeService {

	@Autowired
	private RepairTypeRepository repo;
	
	public Optional<RepairType> findById(int id) {
		return repo.findById(id);
	}
	
	
	public List<RepairType> findAll(){
		return repo.findAll();
	}
	
	public void save(RepairType repairType) {
		repo.save(repairType);
	}
	
	public void delete(RepairType repairType) {
		repo.delete(repairType);
	}
	
	public void update(RepairType repairType) throws Exception {
		RepairType tempRepairType = repo.findById(repairType.getId()).orElse(null);
		
		if (tempRepairType != null) {
			tempRepairType.setName(repairType.getName());
			repo.save(tempRepairType);
		} else {
			throw new Exception("Repair not found");
		}
	}
}
