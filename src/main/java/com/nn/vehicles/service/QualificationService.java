package com.nn.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.vehicles.repository.QualificationRepository;
import com.nn.vehicles.model.Employee;
import com.nn.vehicles.model.Qualification;

@Service
public class QualificationService {

	@Autowired
	private QualificationRepository repo;
	
	public Optional<Qualification> findById(int id) {
		return repo.findById(id);
	}
	
	
	public List<Qualification> findAll(){
		return repo.findAll();
	}
	
	public void save(Qualification qualification) {
		repo.save(qualification);
	}
	
	public void delete(Qualification qualification) {
		repo.delete(qualification);
	}
	
	public void update(Qualification qualification) throws Exception {
		Qualification tempQualification = repo.findById(qualification.getId()).orElse(null);
		
		if (tempQualification != null) {
			tempQualification.setName(qualification.getName());
			tempQualification.setRepairTypes(qualification.getRepairTypes());
			repo.save(tempQualification);
		} else {
			throw new Exception("Qualification not found");
		}
	}
}
