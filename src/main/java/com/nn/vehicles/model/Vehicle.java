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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "vehicles")
public class Vehicle {
	
	@Id
	@Column(name = "vehicle_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Size(min = 8,max = 8,message="Should be 8 symbols long.")
	private String registrationPlate;
	
	@ManyToOne
	@JoinColumn(name = "brand_model_id", nullable = false)
	private BrandModel brandModel;
	
	@Min(value=1970, message = "Production year from 1960 onwards.")
	private int productionYear;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@OneToMany(mappedBy =  "vehicle")
	private Set<Repair> repairs;

	public Vehicle() {
	}

	public Vehicle(String registrationPlate, BrandModel brandModel, int productionYear,
			Customer customer) {
		this.registrationPlate = registrationPlate;
		this.brandModel = brandModel;
		this.productionYear = productionYear;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRegistrationPlate() {
		return registrationPlate;
	}

	public void setRegistrationPlate(String registrationPlate) {
		this.registrationPlate = registrationPlate;
	}

	public BrandModel getBrandModel() {
		return brandModel;
	}

	public void setBrandModel(BrandModel brandModel) {
		this.brandModel = brandModel;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Repair> getRepairs() {
		return repairs;
	}

	public void setRepairs(Set<Repair> repairs) {
		this.repairs = repairs;
	}
}
