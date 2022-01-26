package com.nn.vehicles.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "qualifications")
public class Qualification {

	@Id
	@Column(name = "qualification_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;
	
	@OneToMany(mappedBy =  "qualification")
	private Set<Employee> employees;
	
	@ManyToMany
	@JoinTable(name="qualifications_repairtypes", 
	joinColumns={@JoinColumn(name="qualification_id", nullable = true)}, 
	inverseJoinColumns={@JoinColumn(name="repair_type_id", nullable = true)})
	private Set<RepairType> repairTypes;
	
	
	public Qualification() {
	}


	public Qualification(String name, Set<Employee> employees, Set<RepairType> repairTypes) {
		this.name = name;
		this.employees = employees;
		this.repairTypes = repairTypes;
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


	public Set<Employee> getEmployees() {
		return employees;
	}


	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}


	public Set<RepairType> getRepairTypes() {
		return repairTypes;
	}


	public void setRepairTypes(Set<RepairType> repairTypes) {
		this.repairTypes = repairTypes;
	}
	
	
	
}
