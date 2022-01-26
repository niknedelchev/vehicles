package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.RepairRepository;
import com.nn.vehicles.model.Repair;

@Service
public class RepairService {

	@Autowired
	private RepairRepository repo;
	
	public Optional<Repair> findById(int id) {
		return repo.findById(id);
	}
	
	
	public List<Repair> findAll(){
		return repo.findAll();
	}
	
	public List<Repair> findByVehicleId(int vehicleId){
		return repo.findByVehicleId(vehicleId);
	}
	
	public void save(Repair repair) {
		repo.save(repair);
	}
	
	public void delete(Repair repair) {
		repo.delete(repair);
	}
	
	public void update(Repair repair) throws Exception {
		Repair tempRepair = repo.findById(repair.getId()).orElse(null);
		
		if (tempRepair != null) {
			tempRepair.setRepairShop(repair.getRepairShop());
			tempRepair.setPrice(repair.getPrice());
			tempRepair.setRepairDate(repair.getRepairDate());
			tempRepair.setRepairTime(repair.getRepairTime());
			tempRepair.setRepairType(repair.getRepairType());
			repo.save(tempRepair);
		} else {
			throw new Exception("Repair not found");
		}
	}
	
}
