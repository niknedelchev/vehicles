package com.nn.vehicles.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "repair_types")
public class RepairType {

	@Id
	@Column(name = "repair_type_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@NotBlank
	String name;
	
	@OneToMany(mappedBy =  "repairType")
	private Set<Repair> repairs;
	
	@ManyToMany(mappedBy = "repairTypes")
	private Set<Qualification> qualifications;
	
	
	public RepairType() {
	}

	public RepairType(String name, Set<Repair> repairs, Set<Qualification> qualifications) {
		this.name = name;
		this.repairs = repairs;
		this.qualifications = qualifications;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Repair> getRepair() {
		return repairs;
	}

	public void setRepair(Set<Repair> repairs) {
		this.repairs = repairs;
	}

	public Set<Qualification>  getQualifications() {
		return qualifications;
	}

	public void setQualification(Set<Qualification> qualifications) {
		this.qualifications = qualifications;
	}
	
}
