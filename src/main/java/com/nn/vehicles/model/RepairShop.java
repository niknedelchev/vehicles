package com.nn.vehicles.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "repair_shops")
public class RepairShop {

	@Id
	@Column(name = "repair_shop_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;
	
	@OneToMany(mappedBy = "repairShop")
	private Set<Repair> repairs;
	
	@OneToMany(mappedBy =  "repairShop")
	private Set<Employee> employees;
	
	@ManyToOne
	@JoinColumn(name = "brand_id", nullable = true)
	private Brand exclusiveBrand;
	
	public RepairShop() {
	}
	
	public RepairShop(Set<Repair> repairs, Set<Employee> employees, String name, Brand exclusiveBrand) {
		this.repairs = repairs;
		this.employees = employees;
		this.name = name;
		this.exclusiveBrand = exclusiveBrand;
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

	public Set<Repair> getRepairs() {
		return repairs;
	}

	public void setRepairs(Set<Repair> repairs) {
		this.repairs = repairs;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public Brand getExclusiveBrand() {
		return exclusiveBrand;
	}

	public void setExclusiveBrand(Brand exclusiveBrand) {
		this.exclusiveBrand = exclusiveBrand;
	}
	
}
