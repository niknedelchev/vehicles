package com.nn.vehicles.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "brands")
public class Brand {
	
	@Id
	@Column(name = "brand_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;

	@OneToMany(mappedBy =  "brand")
	private Set<BrandModel> brandModels;
	
	public Brand() {
	}
	
	public Brand(String name, Set<BrandModel> brandModels) {
		this.name = name;
		this.brandModels = brandModels;
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

	public Set<BrandModel> getBrandModels() {
		return brandModels;
	}

	public void setBrandModels(Set<BrandModel> brandModels) {
		this.brandModels = brandModels;
	}
	
}
